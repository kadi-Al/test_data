bash
   pip install flask
from flask import Flask, request, jsonify
import jwt  # For demonstration purposes only; use a proper library to handle JWT (JSON Web Tokens) in real applications

app = Flask(__name__)

# A simple secret key used for decoding the token. In production, this should be kept secure and not hardcoded.
SECRET_KEY = 'your-256-bit-secret'  # Replace with a strong secret key

@app.route('/verify-token', methods=['POST'])
def verify_token():
    data = request.get_json()
    token = data.get('token')

    if not token:
        return jsonify({"error": "Token is missing", "valid": False}), 400

    try:
        # Minimal JWT decoding to check the token's format and integrity (not secure for real applications)
        decoded = jwt.decode(token, SECRET_KEY, algorithms=["HS256"])
        return jsonify({"message": "Token is valid", "valid": True}), 200
    except jwt.InvalidTokenError:
        return jsonify({"error": "Invalid token", "valid": False}), 401

if __name__ == '__main__':
    app.run(debug=True)