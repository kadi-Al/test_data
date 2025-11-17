import os
import hashlib
from cryptography.hazmat.primitives.ciphers.aead import AESGCM
from cryptography.hazmat.primitives.kdf.pbkdf2 import PBKDF2HMAC
from cryptography.hazmat.backends import default_backend

# Constants for key derivation
SALT_SIZE = 16
PBKDF2_ITERATIONS = 100000
KEY_SIZE = 32  # AES-256 requires a 256-bit key (32 bytes)
GCM_TAG_SIZE = 16
GCM_NONCE_SIZE = 12

def derive_key(password: bytes, salt: bytes) -> bytes:
    """Derive a key from the password using PBKDF2."""
    kdf = PBKDF2HMAC(
        algorithm=hashlib.sha256(),
        iterations=PBKDF2_ITERATIONS,
        salt=salt,
        length=KEY_SIZE,
        backend=default_backend()
    )
    return kdf.derive(password)

def encrypt(plaintext: bytes, associated_data: bytes, password: bytes) -> tuple:
    """Encrypt the plaintext using AES-256-GCM."""
    salt = os.urandom(SALT_SIZE)
    key = derive_key(password, salt)
    aesgcm = AESGCM(key)
    
    nonce = os.urandom(GCM_NONCE_SIZE)
    ciphertext = aesgcm.encrypt(nonce, plaintext, associated_data)
    
    return (salt + nonce + ciphertext), salt

def decrypt(ciphertext: bytes, associated_data: bytes, password: bytes, salt: bytes) -> bytes:
    """Decrypt the ciphertext using AES-256-GCM."""
    key = derive_key(password, salt)
    aesgcm = AESGCM(key)
    
    nonce = ciphertext[-GCM_NONCE_SIZE:]
    encrypted_data = ciphertext[:-GCM_NONCE_SIZE]
    
    try:
        plaintext = aesgcm.decrypt(nonce, encrypted_data, associated_data)
        return plaintext
    except Exception as e:
        # Authentication failure or decryption error
        raise ValueError("Authentication failed or invalid ciphertext.")

# Example usage with test vectors
if __name__ == "__main__":
    password = b"mysecretpassphrase"
    associated_data = b"associated data"
    plaintext = b"This is a secret message!"
    
    # Encryption
    encrypted, salt = encrypt(plaintext, associated_data, password)
    print("Encrypted:", encrypted.hex())
    
    # Decryption
    try:
        decrypted_text = decrypt(encrypted, associated_data, password, salt)
        print("Decrypted:", decrypted_text.decode())
    except ValueError as e:
        print("Decryption failed:", str(e))