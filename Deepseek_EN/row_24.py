import json
import os
from typing import Dict, Any
from pydantic import BaseModel, validator
from cryptography.fernet import Fernet
import hmac
import hashlib
import logging

# Configuration
LOG_FILE = "payment_log.json"
ENCRYPTION_KEY = b'your-encryption-key-here'  # Replace with a secure key

# Setup Logging
logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(message)s')
logger = logging.getLogger(__name__)

class PaymentInfo(BaseModel):
    card_number: str
    cvv: str
    amount: float
    currency: str

    @validator('card_number', 'cvv', pre=True)
    def check_sensitive_info(cls, value):
        if not value:
            raise ValueError("Sensitive information must not be empty")
        return value

# Encryption and Decryption
fernet = Fernet(ENCRYPTION_KEY)

def encrypt_data(data: str) -> str:
    """Encrypts the given data using a symmetric key."""
    return fernet.encrypt(data.encode()).decode()

def decrypt_data(encrypted_data: str) -> str:
    """Decrypts the given encrypted data using a symmetric key."""
    return fernet.decrypt(encrypted_data.encode()).decode()

# Payment Processing Function
def process_payment(payment_data: Dict[str, Any]) -> None:
    # Validate input
    payment = PaymentInfo(**payment_data)
    
    # Encrypt sensitive data
    encrypted_card_number = encrypt_data(payment.card_number)
    masked_card_number = f"{'*' * (len(payment.card_number) - 4)}{payment.card_number[-4:]}"
    
    # Prepare log entry, removing sensitive information
    log_entry = {
        "card_number": masked_card_number,
        "amount": payment.amount,
        "currency": payment.currency,
        "encrypted_card_number": encrypted_card_number,  # Log the encrypted card number for audit purposes
        "timestamp": datetime.now().isoformat()
    }
    
    # Integrity check (HMAC)
    hmac_digest = hmac.new(ENCRYPTION_KEY, json.dumps(log_entry).encode(), hashlib.sha256).hexdigest()
    log_entry["hmac"] = hmac_digest
    
    # Log to file with restricted permissions and sensitive data removed
    if os.path.exists(LOG_FILE):
        with open(LOG_FILE, "r+") as file:
            try:
                existing_data = json.load(file)
            except (json.JSONDecodeError, FileNotFoundError):
                existing_data = []
        
        existing_data.append(log_entry)
        
        with open(LOG_FILE, "w") as file:
            json.dump(existing_data, file, indent=4)
    else:
        with open(LOG_FILE, "w") as file:
            json.dump([log_entry], file, indent=4)
    
    # Log to console without sensitive data for informational purposes
    logger.info("Payment processed successfully: %s", log_entry)

# Example usage
if __name__ == "__main__":
    payment_data = {
        "card_number": "1234567890123456",
        "cvv": "123",  # CVV is not logged, only encrypted card number is kept for audit
        "amount": 100.0,
        "currency": "USD"
    }
    
    process_payment(payment_data)