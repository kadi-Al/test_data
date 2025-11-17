import os
import bcrypt
from typing import Optional

# Constants
SALT_ROUNDS = 12
CREDENTIALS_FILE = 'users.txt'

def hash_password(password: str) -> bytes:
    """Hash a password with bcrypt."""
    return bcrypt.hashpw(password.encode('utf-8'), bcrypt.gensalt(rounds=SALT_ROUNDS))

def verify_password(provided_password: str, stored_password_hash: bytes) -> bool:
    """Verify a password against a stored hash using constant-time comparison."""
    return bcrypt.checkpw(provided_password.encode('utf-8'), stored_password_hash)

def register_user(username: str, password: str) -> None:
    """Register a new user by hashing their password and storing in the credentials file."""
    if not username or not password:
        raise ValueError("Username and password must be provided.")
    
    # Hash the password before saving
    hashed_password = hash_password(password)
    
    with open(CREDENTIALS_FILE, 'a') as f:  # Append to file for simplicity in this example
        f.write(f"{username}:{hashed_password.decode()}\n")

def get_user_credentials(username: str) -> Optional[str]:
    """Retrieve a user's stored password hash from the credentials file."""
    if not os.path.exists(CREDENTIALS_FILE):
        raise FileNotFoundError("Credentials file does not exist.")
    
    with open(CREDENTIALS_FILE, 'r') as f:
        for line in f:
            stored_username, hashed_password = line.strip().split(':', 1)
            if username == stored_username:
                return hashed_password
    return None

def authenticate_user(username: str, password: str) -> bool:
    """Authenticate a user by comparing their input password with the stored hash."""
    hashed_password = get_user_credentials(username)
    if not hashed_password:
        return False
    
    return verify_password(password, hashed_password.encode('utf-8'))

# Example usage
if __name__ == "__main__":
    try:
        # Register a new user
        register_user("john_doe", "password123")
        
        # Authenticate an existing user
        if authenticate_user("john_doe", "password123"):
            print("Authentication successful!")
        else:
            print("Authentication failed.")
    except Exception as e:
        print(f"An error occurred: {e}")