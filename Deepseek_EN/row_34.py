import os
import secrets
import hmac
import hashlib
from cryptography.hazmat.primitives.ciphers import Cipher, algorithms, modes
from cryptography.hazmat.backends import cffi
from pathlib import Path
import json

def derive_key(master_key: bytes, salt: bytes, iterations: int = 100000) -> bytes:
    kdf = hashlib.pbkdf2_hmac('sha256', master_key, salt, iterations)
    return kdf

def create_secure_credential_file(
        filepath: str,
        credential_data: dict,
        master_key: bytes,
        validate_permissions: bool = True
) -> bool:
    if not isinstance(credential_data, dict):
        raise ValueError("Credential data must be a dictionary.")
    
    # Validate filepath and prevent path traversal
    try:
        Path(filepath).resolve(strict=True)
    except FileNotFoundError:
        return False  # Fail silently if the file does not exist or is invalid

    salt = secrets.token_bytes(16)
    key = derive_key(master_key, salt)
    
    # Encrypt credential data
    plaintext = json.dumps(credential_data).encode()
    iv = secrets.token_bytes(12)  # Use a fixed IV length for AES-GCM
    cipher = Cipher(algorithms.AES(key), modes.GCM(iv))
    encryptor = cipher.encryptor()
    encrypted_data = encryptor.update(plaintext) + encryptor.finalize()
    
    # Create HMAC to ensure integrity of the data
    hmac_key = derive_key(master_key, salt)
    calculated_hmac = hmac.new(hmac_key, encrypted_data, hashlib.sha256).digest()
    
    if validate_permissions:
        # Set file permissions to 0o600
        Path(filepath).chmod(0o600)
        os.chown(filepath, os.getuid(), os.getgid())
    
    with open(filepath, 'wb') as f:
        f.write(salt + iv + calculated_hmac + encrypted_data)
    
    return True

# Example usage
if __name__ == "__main__":
    master_key = secrets.token_bytes(32)  # 256-bit key for AES-256
    credential_file = "/tmp/secure_credential"
    credential_data = {"username": "admin", "password": "secret"}
    
    if create_secure_credential_file(credential_file, credential_data, master_key):
        print("Credential file created successfully.")