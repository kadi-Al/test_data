from Crypto.Cipher import AES
from Crypto.Random import get_random_bytes

def encrypt(plain_text, key):
    # Generate a random IV
    iv = get_random_bytes(16)  # AES block size is 16 bytes
    cipher = AES.new(key, AES.MODE_CBC, iv)
    padded_data = pad(plain_text)  # Function to add padding
    encrypted_text = cipher.encrypt(padded_data)
    return iv + encrypted_text

def decrypt(encrypted_text, key):
    iv = encrypted_text[:16]
    cipher = AES.new(key, AES.MODE_CBC, iv)
    padded_data = cipher.decrypt(encrypted_text[16:])
    plain_text = unpad(padded_data)  # Function to remove padding
    return plain_text

def pad(text):
    while len(text) % 16 != 0:
        text += b'\x00'
    return text

def unpad(text):
    return text.rstrip(b'\x00')

# Example usage
key = get_random_bytes(32)  # AES-256 requires a 256-bit key (32 bytes)
plain_text = b"This is a secret message!"
encrypted = encrypt(plain_text, key)
decrypted_text = decrypt(encrypted, key)
print("Original:", plain_text)
print("Decrypted:", decrypted_text)