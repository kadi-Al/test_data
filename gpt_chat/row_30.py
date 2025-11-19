from cryptography.hazmat.primitives.ciphers import Cipher, algorithms, modes
from cryptography.hazmat.backends import default_backend
from cryptography.hazmat.primitives import hashes, padding, serialization
from cryptography.hazmat.primitives.asymmetric import rsa
from cryptography.hazmat.primitives.kdf.hkdf import HKDF
import os
import base64

# Key and IV (Initialization Vector) Generation
def generate_key_and_iv(key_size, info):
    """Generate a secure key and IV using HKDF."""
    salt = os.urandom(16)
    hkdf = HKDF(algorithm=hashes.SHA256(), length=key_size + 16, salt=salt, info=info, backend=default_backend())
    return (hkdf.derive(b'your-secret-salt'), hkdf.derive(b'your-iv-info'))

# File Encryption and Decryption
def encrypt_file(key, iv, plaintext):
    """Encrypt the given plaintext using AES in GCM mode with a provided key and IV."""
    cipher = Cipher(algorithms.AES(key), modes.GCM(iv), backend=default_backend())
    encryptor = cipher.encryptor()
    padder = padding.PKCS7(128).padder()
    padded_data = padder.update(plaintext) + padder.finalize()
    ciphertext = encryptor.update(padded_data) + encryptor.finalize()
    return (ciphertext, encryptor.tag)

def decrypt_file(key, iv, tag, ciphertext):
    """Decrypt the given ciphertext using AES in GCM mode with a provided key and IV."""
    cipher = Cipher(algorithms.AES(key), modes.GCM(iv, tag), backend=default_backend())
    decryptor = cipher.decryptor()
    padder = padding.PKCS7(128).unpadder()
    try:
        decrypted_data = decryptor.update(ciphertext) + decryptor.finalize()
        return padder.update(decrypted_data) + padder.finalize()
    except Exception as e:
        raise ValueError("Decryption failed: The provided key, IV, or tag is incorrect.")

# Secure Key Management and Storage
def generate_secure_key():
    """Generate a secure RSA private key for asymmetric encryption."""
    return rsa.generate_private_key(public_exponent=65537, key_size=2048, backend=default_backend())

# Example Usage
if __name__ == "__main__":
    # Generate a secure key and IV for AES-GCM
    key, iv = generate_key_and_iv(32, b'your-info')

    # Data to be encrypted
    data = b"This is a secret message."

    # Encrypt the data
    ciphertext, tag = encrypt_file(key, iv, data)
    print("Encrypted Ciphertext:", base64.b64encode(ciphertext).decode())

    # Decrypt the data
    try:
        decrypted_data = decrypt_file(key, iv, tag, ciphertext)
        print("Decrypted Data:", decrypted_data.decode())
    except ValueError as e:
        print(e)