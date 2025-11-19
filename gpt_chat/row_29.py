import hmac
import secrets
from hashlib import sha256

# Function to generate a secure random key for HMAC
def generate_hmac_key():
    """Generates a secure, unpredictable secret key using the secrets module."""
    return secrets.token_bytes(32)  # Generate a 256-bit (32 bytes) key

# Initialize an HMAC context with SHA-256 hashing algorithm and a secure random key
def initialize_hmac(key):
    """Initializes the HMAC context using the provided secret key."""
    return hmac.new(key, digestmod=sha256)

# Function to update the HMAC context with data
def update_hmac(hmac_context, data):
    """Updates the HMAC context with input data."""
    hmac_context.update(data)

# Function to finalize and compute the HMAC digest for the provided data
def final_hmac(hmac_context):
    """Finalizes the HMAC computation and returns the digest."""
    return hmac_context.digest()

# Function to secure hash a message using HMAC with SHA-256
def secure_hash_message(key, message):
    """Generates an HMAC digest of the given message using the provided key."""
    if not isinstance(key, bytes) or len(key) != 32:
        raise ValueError("Key must be a 256-bit (32 byte) secure random key.")

    hmac_context = initialize_hmac(key)
    update_hmac(hmac_context, message)
    return final_hmac(hmac_context)

# Example usage of the HMAC functionality
if __name__ == "__main__":
    # Generate a secure key for use in HMAC
    secret_key = generate_hmac_key()
    print("Generated Secret Key:", secret_key.hex())

    message = b"sessionToken=abc123;userID=456;"
    hashed_message = secure_hash_message(secret_key, message)
    print("HMAC-SHA256 Hash of the Message:", hashed_message.hex())