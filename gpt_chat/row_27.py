from cryptography.hazmat.primitives.ciphers import Cipher, algorithms, modes
from cryptography.hazmat.primitives import padding
from cryptography.hazmat.primitives.asymmetric import utils
from cryptography.hazmat.backends import default_backend
from cryptography.hazmat.primitives import hashes
import os
import base64

# Function to generate a secure key using PBKDF2 with HMAC and SHA-256
def derive_key(password, salt):
    kdf = hashes.PBKDF2HMAC(
        algorithm=hashes.SHA256(),
        length=32,  # AES-256 requires a key of 32 bytes (256 bits)
        iterations=100000,  # High number for security against brute force attacks
    )
    return base64.urlsafe_b64encode(kdf.derive(password.encode()))

# Function to generate a secure random salt
def generate_salt():
    return os.urandom(16)  # AES typically uses a 16-byte (128-bit) IV and key

# Function to encrypt data using AES in GCM mode with a derived key
def encrypt(plaintext, password):
    salt = generate_salt()
    key = derive_key(password, salt)

    # Generate a random initialization vector (IV)
    iv = os.urandom(12)  # AES-GCM IV is usually 96 bits or 12 bytes

    cipher = Cipher(algorithms.AES(key), modes.GCM(iv), backend=default_backend())
    encryptor = cipher.encryptor()

    # Pad the plaintext to be a multiple of the block size (16 bytes for AES)
    padder = padding.PKCS7(128).padder()
    padded_data = padder.update(plaintext) + padder.finalize()

    ciphertext = encryptor.update(padded_data) + encryptor.finalize()

    # Return the IV, encrypted data, salt, and tag used for verification in GCM
    return base64.urlsafe_b64encode(iv + ciphertext), base64.urlsafe_b64encode(salt), base64.urlsafe_b64encode(encryptor.tag)

# Function to decrypt data using AES in GCM mode with the same derived key
def decrypt(ciphertext, salt, tag, password):
    key = derive_key(password, salt)

    # Decode base64 encoded ciphertext and IV
    decoded_ciphertext = base64.urlsafe_b64decode(ciphertext)
    iv = decoded_ciphertext[:12]  # Extract the first 12 bytes for IV in AES-GCM
    actual_ciphertext = decoded_ciphertext[12:]

    cipher = Cipher(algorithms.AES(key), modes.GCM(iv, tag), backend=default_backend())
    decryptor = cipher.decryptor()

    # Decrypt the data and verify the integrity with the provided tag
    padded_plaintext = decryptor.update(actual_ciphertext) + decryptor.finalize()

    # Unpad the plaintext to get back the original length of the message
    unpadder = padding.PKCS7(128).unpadder()
    return unpadder.update(padded_plaintext) + unpadder.finalize()

# Example usage
password = "mysecretpassword"
original_data = b"This is a secret message!"

encrypted_data, salt, tag = encrypt(original_data, password)
decrypted_data = decrypt(encrypted_data, salt, tag, password)

print("Original:", original_data)
print("Decrypted:", decrypted_data)