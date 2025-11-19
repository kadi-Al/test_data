import os
import tempfile
import atexit
from cryptography.fernet import Fernet, InvalidToken
from cryptography.hazmat.primitives.kdf.pbkdf2 import PBKDF2HMAC
from cryptography.hazmat.backends import default_backend
import base64
import logging

# Configure logging to avoid exposing sensitive data in logs
logging.basicConfig(level=logging.INFO, format='%(message)s')
logger = logging.getLogger(__name__)

def generate_key(password, salt):
    kdf = PBKDF2HMAC(
        algorithm=default_backend().hmac_algorithm_module.algorithms.SHA256(),
        length=32,
        salt=salt,
        iterations=100000,
        backend=default_backend()
    )
    key = base64.urlsafe_b64encode(kdf.derive(password))
    return key

def encrypt_data(key, data):
    fernet = Fernet(key)
    encrypted_data = fernet.encrypt(data.encode())
    return encrypted_data

def decrypt_data(key, encrypted_data):
    try:
        fernet = Fernet(key)
        decrypted_data = fernet.decrypt(encrypted_data).decode()
        return decrypted_data
    except InvalidToken as e:
        raise Exception("Failed to decrypt API token: " + str(e))
    except ValueError as e:
        raise Exception("Failed to decrypt API token: " + str(e))

def secure_temp_file():
    temp_dir = tempfile.gettempdir()
    file_path = os.path.join(temp_dir, 'api_token')
    with open(file_path, 'wb') as f:
        atexit.register(lambda: os.remove(file_path) if os.path.exists(file_path) else None)
        yield file_path

def save_api_token(token):
    salt = os.urandom(16)
    key = generate_key(b'superstrongandlongmasterpassword', salt)
    encrypted_token = encrypt_data(key, token)
    with secure_temp_file() as file_path:
        with open(file_path, 'wb') as file:
            file.write(encrypted_token)
    logger.info("API Token has been saved.")

def load_api_token():
    salt = os.urandom(16)
    key = generate_key(b'superstrongandlongmasterpassword', salt)
    with secure_temp_file() as file_path:
        with open(file_path, 'rb') as file:
            encrypted_token = file.read()
    try:
        decrypted_token = decrypt_data(key, encrypted_token)
        username, password = decrypted_token.split(':', 1)
        return username, password
    except (InvalidToken, ValueError) as e:
        raise Exception("Failed to decrypt API token: " + str(e))

# Example usage
if __name__ == "__main__":
    try:
        api_token = 'your_api_token_here'  # Replace with actual API token or use environment variables securely
        save_api_token(api_token)
        print("API Token saved.")

        loaded_username, loaded_password = load_api_token()
        print(f"Loaded Username: {loaded_username}, Password: {loaded_password}")
    except Exception as e:
        logger.error("Error: " + str(e))