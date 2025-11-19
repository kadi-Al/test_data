from cryptography.hazmat.primitives.ciphers import Cipher, algorithms, modes
from cryptography.hazmat.backends import default_backend
import os
import base64

# Function to generate a secure random key and IV
def generate_key_and_iv():
    backend = default_backend()
    key = os.urandom(32)  # AES-256 requires 32 bytes key
    iv = os.urandom(12)   # GCM mode uses a 96-bit IV, which is equivalent to 12 bytes
    return key, iv

# Function to encrypt user data
def encrypt(plaintext):
    backend = default_backend()
    key, iv = generate_key_and_iv()

    # Ensure the plaintext length is a multiple of the AES block size (16 bytes)
    padder = padding.PKCS7(128).padder()
    padded_data = padder.update(plaintext) + padder.finalize()

    cipher = Cipher(algorithms.AES(key), modes.GCM(iv), backend=backend)
    encryptor = cipher.encryptor()
    encrypted_data = encryptor.update(padded_data) + encryptor.finalize()

    # Include the IV in the output for potential reuse or decryption later
    return base64.urlsafe_b64encode(encrypted_data), base64.urlsafe_b64encode(iv), encryptor.tag

# Function to decrypt user data
def decrypt(encrypted_data, iv, tag):
    backend = default_backend()
    encrypted_data = base64.urlsafe_b64decode(encrypted_data)
    iv = base64.urlsafe_b64decode(iv)
    tag = base64.urlsafe_b64decode(tag)

    cipher = Cipher(algorithms.AES(key), modes.GCM(iv, tag), backend=backend)
    decryptor = cipher.decryptor()

    try:
        padded_plaintext = decryptor.update(encrypted_data) + decryptor.finalize()
        unpadder = padding.PKCS7(128).unpadder()
        plaintext = unpadder.update(padded_plaintext) + unpadder.finalize()
        return plaintext
    except Exception as e:
        raise ValueError("Decryption failed: The provided data is incorrect or tampered.")

# Example usage
if __name__ == "__main__":
    user_data = b"This is a secret message!"

    encrypted, iv, tag = encrypt(user_data)
    print("Encrypted:", encrypted)

    decrypted_data = decrypt(encrypted, iv, tag)
    print("Decrypted:", decrypted_data.decode())