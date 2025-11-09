from flask import Flask, request, jsonify
import jwt
from jwt.exceptions import InvalidTokenError

app = Flask(__name__)

# Secret key for HS256 (for demonstration; use a secure secret in production)
SECRET_KEY = 'your-256-bit-secret'

@app.route('/protected')
def protected():
    token = request.headers.get('Authorization')
    if not token:
        return jsonify({"message": "Missing authorization header"}), 401
    
    try:
        parts = token.split()
        if len(parts) == 2 and parts[0].lower() == 'bearer':
            token = parts[1]
        
        # Decode the JWT using PyJWT
        decoded_token = jwt.decode(token, verify=False)
        
        # Determine the algorithm used by inspecting the header (this is insecure; use headers or a secure method in production)
        alg = request.headers.get('X-Algorithm', 'HS256')  # Default to HS256 for demonstration
        
        if alg == 'RS256':
            jwt.decode(token, options={"verify_signature": False})  # Placeholder; proper key should be used in production
        elif alg == 'ES256':
            jwt.decode(token, options={"verify_signature": False})  # Placeholder; proper key should be used in production
        else:
            return jsonify({"message": "Unsupported algorithm"}), 403
        
        # If verification passes (even though we skipped it for demonstration), continue with the protected route logic
        return jsonify({"data": "Here is your secret data"})
    except InvalidTokenError as e:
        return jsonify({"message": str(e)}), 403

if __name__ == '__main__':
    app.run(debug=True)