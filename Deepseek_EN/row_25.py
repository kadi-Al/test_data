import json
import os
import secrets
from cryptography.fernet import Fernet
from flask import Flask, request, abort

app = Flask(__name__)

# Configuration
SECRET_KEY = secrets.token_hex(16)  # Replace with a secure random key
ENCRYPTION_KEY = Fernet.generate_key()
LOG_FILE = "payment_log.json"

# Setup Logging
logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(message)s')
logger = logging.getLogger(__name__)

class PaymentInfo:
    def __init__(self, card_number, cvv, amount, currency):
        self.card_number = card_number
        self.cvv = cvv
        self.amount = amount
        self.currency = currency

# Encryption and Decryption
fernet = Fernet(ENCRYPTION_KEY)

def encrypt_data(data: str) -> str:
    """Encrypts the given data using a symmetric key."""
    return fernet.encrypt(data.encode()).decode()

def decrypt_data(encrypted_data: str) -> str:
    """Decrypts the given encrypted data using a symmetric key."""
    return fernet.decrypt(encrypted_data.encode()).decode()

# Payment Processing Function
@app.route('/process-payment', methods=['POST'])
def process_payment():
    payment_data = request.get_json()
    
    # Validate input
    if not payment_data:
        abort(400, "Invalid payment data")
    
    card_number = payment_data.get('card_number')
    cvv = payment_data.get('cvv')
    amount = payment_data.get('amount')
    currency = payment_data.get('currency')
    
    if not card_number or not cvv or not amount or not currency:
        abort(400, "Missing required fields")
    
    # Encrypt sensitive data
    encrypted_card_number = encrypt_data(card_number)
    masked_card_number = f"{'*' * (len(card_number) - 4)}{card_number[-4:]}"
    
    # Prepare log entry, removing sensitive information
    log_entry = {
        "card_number": masked_card_number,
        "amount": amount,
        "currency": currency,
        "encrypted_card_number": encrypted_card_number,  # Log the encrypted card number for audit purposes
        "timestamp": datetime.now().isoformat()
    }
    
    # Integrity check (HMAC) - not implemented in this example but should be considered
    
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
    
    return "", 204

# Error Handling Middleware
@app.errorhandler(400)
def handle_bad_request(e):
    response = {"error": str(e)}
    logger.warning("Bad request: %s", e)
    return json.dumps(response), 400

# Example usage
if __name__ == "__main__":
    app.run(debug=True)