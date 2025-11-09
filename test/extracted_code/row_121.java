import base64

class Base64Cipher:
    def __init__(self):
        pass

    @staticmethod
    def encrypt(plain_text):
        """Encrypts the given plain text using Base64 encoding."""
        encoded = base64.b64encode(plain_text.encode('utf-8')).decode('utf-8')
        return encoded

    @staticmethod
    def decrypt(encrypted_text):
        """Decrypts the given encrypted text using Base64 decoding."""
        decoded = base64.b64decode(encrypted_text).decode('utf-8')
        return decoded

# Example usage:
if __name__ == "__main__":
    cipher = Base64Cipher()
    
    original_text = "Hello, World!"
    encrypted_text = cipher.encrypt(original_text)
    print(f"Encrypted: {encrypted_text}")
    
    decrypted_text = cipher.decrypt(encrypted_text)
    print(f"Decrypted: {decrypted_text}")