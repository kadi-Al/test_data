import os
import secrets
from typing import Tuple, Optional
from cryptography.hazmat.primitives.ciphers import Cipher, algorithms, modes
from cryptography.hazmat.primitives import hashes, hmac
from cryptography.hazmat.primitives.kdf.pbkdf2 import PBKDF2HMAC
from cryptography.hazmat.backends import default_backend
import base64

class SecureDataEncryptor:
    """
    Enhanced implementation for secure AES-GCM encryption and decryption
    """
    
    def __init__(self, password: bytes, salt: Optional[bytes] = None):
        if not isinstance(password, bytes) or len(password) < 8:
            raise ValueError("Password must be at least 8 characters long")
        
        self.backend = default_backend()
        self.salt = salt or secrets.token_bytes(16)
        
        # Derive key using PBKDF2 with HMAC SHA-256
        kdf = PBKDF2HMAC(
            algorithm=hashes.SHA256(),
            length=32,
            salt=self.salt,
            iterations=100000,  # Minimum recommended for practical use
            backend=self.backend
        )
        self.key = kdf.derive(password)
    
    def encrypt(self, plaintext: bytes, associated_data: Optional[bytes] = None) -> Tuple[bytes, bytes, bytes]:
        """
        Encrypt the given plaintext using AES-GCM
        
        Args:
            plaintext (bytes): Data to be encrypted
            associated_data (Optional[bytes]): Additional authenticated data
            
        Returns:
            Tuple of (ciphertext, iv, authentication_tag)
        """
        if not isinstance(plaintext, bytes):
            raise TypeError("Plaintext must be bytes")
        
        # Generate a secure random IV for each encryption operation
        iv = secrets.token_bytes(12)  # Use 12 bytes IV as recommended in GCM mode
        
        # Create and configure the cipher object
        cipher = Cipher(algorithms.AES(self.key), modes.GCM(iv), backend=self.backend)
        encryptor = cipher.encryptor()
        
        # Add associated data if provided
        if associated_data:
            encryptor.authenticate_additional_data(associated_data)
        
        # Encrypt the plaintext and finalize the encryption
        ciphertext = encryptor.update(plaintext) + encryptor.finalize()
        
        return ciphertext, iv, encryptor.tag
    
    def decrypt(self, ciphertext: bytes, iv: bytes, tag: bytes, associated_data: Optional[bytes] = None) -> bytes:
        """
        Decrypt the given ciphertext using AES-GCM
        
        Args:
            ciphertext (bytes): Encrypted data to be decrypted
            iv (bytes): Initialization vector used during encryption
            tag (bytes): Authentication tag used for integrity checking
            associated_data (Optional[bytes]): Additional authenticated data provided during encryption
            
        Returns:
            Decrypted plaintext
            
        Raises:
            ValueError: If the authentication fails or inputs are invalid
        """
        if not all(isinstance(x, bytes) for x in (ciphertext, iv, tag)):
            raise TypeError("All inputs must be of type bytes")
        
        if len(iv) != 12:
            raise ValueError("IV must be exactly 12 bytes long")
        
        # Create a cipher object with the derived key and IV/tag
        cipher = Cipher(algorithms.AES(self.key), modes.GCM(iv, tag), backend=self.backend)
        decryptor = cipher.decryptor()
        
        # Add associated data if provided
        if associated_data:
            decryptor.authenticate_additional_data(associated_data)
        
        try:
            plaintext = decryptor.update(ciphertext) + decryptor.finalize()
            return plaintext
        except ValueError as e:
            raise ValueError("Authentication failed: " + str(e))
    
    def secure_compare(self, a: bytes, b: bytes) -> bool:
        """
        Constant-time comparison to prevent timing attacks
        """
        if not isinstance(a, bytes) or not isinstance(b, bytes):
            raise TypeError("Inputs must be of type bytes")
        
        return secrets.compare_digest(a, b)
    
    def __del__(self):
        # Zero out the key material when the object is destroyed to prevent memory exposure
        for i in range(len(self.key)):
            self.key[i] = 0

# Example usage with security testing
def test_encryption():
    """Security test cases for the encryption implementation"""
    
    # Test with sample data
    password = b"my_secure_password_123"
    encryptor = SecureDataEncryptor(password)
    
    plaintext = b"Sensitive user data that needs protection"
    
    # Encrypt
    ciphertext, iv, tag = encryptor.encrypt(plaintext)
    
    # Decrypt
    decrypted = encryptor.decrypt(ciphertext, iv, tag)
    
    # Verify integrity
    assert encryptor.secure_compare(plaintext, decrypted), "Decryption failed"
    
    # Test tamper detection
    try:
        tampered_ciphertext = bytearray(ciphertext)
        tampered_ciphertext[0] ^= 0x01  # Flip one bit
        encryptor.decrypt(bytes(tampered_ciphertext), iv, tag)
        assert False, "Tampering should be detected"
    except ValueError:
        pass  # Expected behavior
    
    print("All security tests passed")

if __name__ == "__main__":
    test_encryption()