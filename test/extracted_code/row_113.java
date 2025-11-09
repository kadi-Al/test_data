from Crypto.Cipher import DES, DES3
import base64

class EncryptionHelper:
    def __init__(self, key, algorithm='des'):
        if algorithm == 'des':
            self.cipher = DES.new(key, DES.MODE_ECB)
        elif algorithm == 'desede':
            self.cipher = DES3.new(key, DES3.MODE_ECB)
        else:
            raise ValueError("Unsupported algorithm. Use 'des' or 'desede'.")
    
    def encrypt(self, plaintext):
        # Pad the plaintext to be a multiple of 8 bytes
        while len(plaintext) % 8 != 0:
            plaintext += b'\0'
        ciphertext = self.cipher.encrypt(plaintext)
        return base64.b64encode(ciphertext).decode('utf-8')
    
    def decrypt(self, ciphertext):
        ciphertext = base64.b64decode(ciphertext.encode('utf-8'))
        plaintext = self.cipher.decrypt(ciphertext)
        # Remove padding if any
        return plaintext.rstrip(b'\0').decode('utf-8')

# Example usage
key = b'secret!'  # Must be 8 bytes for DES, and 24 bytes for DESede
encryption_helper = EncryptionHelper(key, algorithm='des')

plaintext = "Hello, World!"
encrypted_text = encryption_helper.encrypt(plaintext.encode('utf-8'))
print("Encrypted:", encrypted_text)

decrypted_text = encryption_helper.decrypt(encrypted_text)
print("Decrypted:", decrypted_text)