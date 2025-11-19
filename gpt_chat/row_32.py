import os
import tempfile
import atexit
from cryptography.fernet import Fernet

def generate_key():
    """Generate and return a key for symmetric encryption."""
    return Fernet.generate_key()

def encrypt_data(key, data):
    """Encrypt the given data using the provided key."""
    f = Fernet(key)
    encrypted_data = f.encrypt(data.encode())
    return encrypted_data

def decrypt_data(key, encrypted_data):
    """Decrypt the given encrypted data using the provided key."""
    f = Fernet(key)
    decrypted_data = f.decrypt(encrypted_data).decode()
    return decrypted_data

class SecureCache:
    def __init__(self, secret_key=None):
        if not secret_key:
            secret_key = generate_key()
        self.secret_key = secret_key
        self.temp_file_path = None

    def cache(self, data):
        """Cache the given sensitive data."""
        # Generate a temporary file with restricted permissions
        temp_dir = tempfile.gettempdir()
        self.temp_file_path = os.path.join(temp_dir, "secret_data")

        # Write the encrypted data to the temporary file
        encrypted_data = encrypt_data(self.secret_key, data)
        with open(self.temp_file_path, 'wb') as f:
            f.write(encrypted_data)

        # Set restrictive permissions on the temporary file
        os.chmod(self.temp_file_path, 0o600)

        # Register a function to delete the file at exit
        atexit.register(lambda: self._delete_on_exit())

    def retrieve(self):
        """Retrieve and return the cached sensitive data."""
        if not os.path.exists(self.temp_file_path):
            raise FileNotFoundError("No secret data is currently cached.")

        # Read the encrypted data from the temporary file
        with open(self.temp_file_path, 'rb') as f:
            encrypted_data = f.read()

        # Decrypt and return the data
        decrypted_data = decrypt_data(self.secret_key, encrypted_data)
        return decrypted_data

    def _delete_on_exit(self):
        """Delete the cached file when the program exits."""
        if os.path.exists(self.temp_file_path):
            os.remove(self.temp_file_path)

# Example usage:
if __name__ == "__main__":
    key = generate_key()  # Generate or load a secret key securely
    cache = SecureCache(secret_key=key)

    sensitive_data = "SuperSecretData"
    cache.cache(sensitive_data)  # Cache the data

    retrieved_data = cache.retrieve()  # Retrieve and decrypt the data
    print("Retrieved Data:", retrieved_data)