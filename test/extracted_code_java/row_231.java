from flask import Flask, request, jsonify, abort

app = Flask(__name__)

# Example admin-only functionality
def is_admin():
    # This function should check if the user has admin privileges
    return request.headers.get('X-Admin-Key') == 'true'

@app.route('/debug-endpoint', methods=['GET'])
def debug_endpoint():
    if not request.args.get('debug') == 'true':
        abort(403)  # Forbidden
    
    if not is_admin():
        abort(403)  # Forbidden
    
    # Admin-only functionality here
    return jsonify({"message": "Admin functionality accessed"})

if __name__ == '__main__':
    app.run(debug=True)
from flask import Flask, request, jsonify, abort
import jwt

app = Flask(__name__)

# Secret key for encoding and decoding tokens
SECRET_KEY = 'your-secret-key'

def decode_token(token):
    try:
        return jwt.decode(token, SECRET_KEY, algorithms=['HS256'])
    except jwt.ExpiredSignatureError:
        return None  # Token has expired
    except jwt.InvalidTokenError:
        return None  # Invalid token

@app.route('/debug-endpoint', methods=['GET'])
def debug_endpoint():
    token = request.headers.get('X-Debug-Token')
    if not token:
        abort(401)  # Unauthorized
    
    payload = decode_token(token)
    if not payload or not payload.get('debug') == 'true':
        abort(403)  # Forbidden
    
    if not is_admin():
        abort(403)  # Forbidden
    
    # Admin-only functionality here
    return jsonify({"message": "Admin functionality accessed"})

if __name__ == '__main__':
    app.run(debug=True)