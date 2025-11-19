import os
import json
import logging
from flask import Flask, request, abort
from cryptography.fernet import Fernet
from datetime import datetime
import hashlib

app = Flask(__name__)

# Generate a key for encryption and decryption
# This should be stored securely, ideally in an environment variable or secure vault
key = Fernet.generate_key()
cipher_suite = Fernet(key)

# Configure logging to log sanitized data only
logging.basicConfig(level=logging.INFO)
logger = logging.getLogger('payment-service')

def is_valid_card_number(card_number):
    """ Validate card number using a simple Luhn algorithm for demonstration purposes. """
    if len(card_number) != 16 or not card_number.isdigit():
        return False
    total = 0
    for i, digit in enumerate(reversed(card_number)):
        if i % 2 == 1:
            digit = int(digit) * 2
            if digit > 9:
                digit -= 9
        total += digit
    return total % 10 == 0

def encrypt_data(data):
    """ Encrypt sensitive payment data. """
    return cipher_suite.encrypt(json.dumps(data).encode()).decode()

def decrypt_data(encrypted_data):
    """ Decrypt sensitive payment data. """
    return json.loads(cipher_suite.decrypt(encrypted_data.encode()).decode())

@app.route('/process-payment', methods=['POST'])
def process_payment():
    try:
        # Get and validate input
        payment_data = request.get_json()
        if not payment_data or 'card_number' not in payment_data or not is_valid_card_number(payment_data['card_number']):
            abort(400, description="Invalid card number")

        # Encrypt sensitive data before processing the payment
        encrypted_pii = encrypt_data(payment_data)
        logger.info(f"Received payment request: {encrypted_pii}")  # Log sanitized data

        # Simulate processing (in a real application, this would be where you'd handle transactions with gateways)
        # Here we just return the encrypted data as confirmation
        return json.dumps({'status': 'success', 'transaction_id': hashlib.sha256(str(datetime.now()).encode()).hexdigest(), 'encrypted_pii': encrypted_pii})
    except Exception as e:
        logger.error(f"Payment processing failed: {str(e)}")  # Log sanitized error message
        abort(500, description=str(e))

if __name__ == '__main__':
    app.run(debug=False, host='0.0.0.0', port=int(os.environ.get('PORT', 8080)))