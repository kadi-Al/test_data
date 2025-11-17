from cryptography.hazmat.primitives.ciphers import Cipher, algorithms, modes
from cryptography.hazmat.primitives import hashes, padding, keywrap
from cryptography.hazmat.primitives.kdf.pbkdf2 import PBKDF2HMAC
from cryptography.hazmat.backends import default_backend
import os
import secrets
import base64

class SecureFileEncryptor:
    def __init__(self, password, salt=None, iterations=100000):
        self.password = password
        if not salt:
            self.salt = os.urandom(16)
        else:
            self.salt = salt
        self.iterations = iterations
        self._derive_key()

    def _derive_key(self):
        kdf = PBKDF2HMAC(
            algorithm=hashes.SHA256(),
            length=32,
            salt=self.salt,
            iterations=self.iterations,
            backend=default_backend()
        )
        self.key = keywrap.wrapping_key(kdf.derive(self.password.encode()))

    def encrypt(self, file_path):
        if not os.path.exists(file_path):
            raise FileNotFoundError("The specified file does not exist.")
        
        with open(file_path, 'rb') as f:
            plaintext = f.read()
        
        padder = padding.PKCS7(algorithms.AES.block_size).padder()
        padded_data = padder.update(plaintext) + padder.finalize()

        iv = secrets.token_bytes(12)
        cipher = Cipher(algorithms.AES(self.key), modes.GCM(iv), backend=default_backend())
        encryptor = cipher.encryptor()
        encrypted_data = encryptor.update(padded_data) + encryptor.finalize()

        with open(file_path + '.enc', 'wb') as f:
            f.write(self.salt + iv + encryptor.tag + encrypted_data)
        
        return base64.urlsafe_b64encode(self.salt + iv + encryptor.tag + encrypted_data).decode()

    def decrypt(self, encrypted_file_path):
        if not os.path.exists(encrypted_file_path):
            raise FileNotFoundError("The specified file does not exist.")
        
        with open(encrypted_file_path, 'rb') as f:
            data = f.read()
        
        self.salt = data[:16]
        iv = data[16:28]
        tag = data[28:44]
        encrypted_data = data[44:]

        kdf = PBKDF2HMAC(
            algorithm=hashes.SHA256(),
            length=32,
            salt=self.salt,
            iterations=self.iterations,
            backend=default_backend()
        )
        key = keywrap.wrapping_key(kdf.derive(self.password.encode()))

        cipher = Cipher(algorithms.AES(key), modes.GCM(iv, tag), backend=default_backend())
        decryptor = cipher.decryptor()
        
        try:
            decrypted_data = decryptor.update(encrypted_data) + decryptor.finalize()
        except ValueError:
            raise ValueError("Authentication failed or tampered data detected.")
        
        padder = padding.PKCS7(algorithms.AES.block_size).unpadder()
        plaintext = padder.update(decrypted_data) + padder.finalize()

        with open(encrypted_file_path[:-4], 'wb') as f:
            f.write(plaintext)

# Example usage
if __name__ == "__main__":
    password = "mysecretpassword"
    salt = os.urandom(16)
    encryptor = SecureFileEncryptor(password, salt)
    
    file_path = "example.txt"
    encrypted_file_path = file_path + '.enc'
    
    print("Encrypting file...")
    encrypted_data = encryptor.encrypt(file_path)
    print("Encrypted data:", encrypted_data)
    
    print("Decrypting file...")
    encryptor.decrypt(encrypted_file_path)
    print("File decrypted.")