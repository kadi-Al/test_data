import os
import tempfile
import atexit
from cryptography.fernet import Fernet, InvalidToken
from cryptography.hazmat.primitives.kdf.pbkdf2 import PBKDF2HMAC
from cryptography.hazmat.backends import default_backend
import base64

# Ensure the secure key generation and encryption are handled properly
def generate_key(password, salt):
    """Generate a key from password using PBKDF2."""
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
    """Encrypt the given data using the provided key."""
    fernet = Fernet(key)
    encrypted_data = fernet.encrypt(data.encode())
    return encrypted_data

def decrypt_data(key, encrypted_data):
    """Decrypt the given encrypted data using the provided key."""
    fernet = Fernet(key)
    try:
        decrypted_data = fernet.decrypt(encrypted_data).decode()
    except InvalidToken:
        raise ValueError("Invalid key or token.")
    return decrypted_data

def secure_temp_file():
    """Create a temporary file with restricted permissions."""
    temp_dir = tempfile.gettempdir()
    fd, path = tempfile.mkstemp(dir=temp_dir, suffix='.secret', mode='wb')
    os.chmod(path, 0o600)
    atexit.register(os.remove, path)
    return path

def save_credentials(username, password):
    """Save credentials to a secure temporary file."""
    # Generate a random salt for key derivation
    salt = os.urandom(16)
    # Derive the encryption key from a strong master password (not hardcoded!)
    master_password = b'superstrongandlongmasterpassword'  # Example, use a secure method to handle this in real applications!
    key = generate_key(master_password, salt)

    credentials = f"{username}:{password}"
    encrypted_data = encrypt_data(key, credentials)

    with open(secure_temp_file(), 'wb') as file:
        file.write(encrypted_data)

def load_credentials():
    """Load and decrypt the stored credentials."""
    path = secure_temp_file()
    if not os.path.exists(path):
        raise FileNotFoundError("No credential file found.")

    with open(path, 'rb') as file:
        encrypted_data = file.read()

    master_password = b'superstrongandlongmasterpassword'  # Example, use a secure method to handle this in real applications!
    key = generate_key(master_password, salt=os.urandom(16))

    try:
        decrypted_data = decrypt_data(key, encrypted_data)
    except (InvalidToken, ValueError) as e:
        raise Exception("Failed to decrypt credentials: " + str(e))

    username, password = decrypted_data.split(':', 1)
    return username, password

# Example usage
if __name__ == "__main__":
    try:
        save_credentials('admin', 'adminpassword')
        print("Credentials saved.")

        loaded_username, loaded_password = load_credentials()
        print(f"Loaded Username: {loaded_username}, Password: {loaded_password}")
    except Exception as e:
        print("Error:", str(e))