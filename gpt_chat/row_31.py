from flask import Flask, request, jsonify, session, abort
import hashlib
import os
import secrets
from functools import wraps

app = Flask(__name__)
app.secret_key = os.urandom(24)  # Generate a secure secret key
CSRF_TOKEN_KEY = 'csrf_token'
RATELIMIT_HEADERS = {'X-RateLimit-Remaining': '10', 'X-RateLimit-Reset': '3600'}

def generate_csrf_token():
    if CSRF_TOKEN_KEY not in session:
        session[CSRF_TOKEN_KEY] = secrets.token_hex(16)
    return session[CSRF_TOKEN_KEY]

def login_required(f):
    @wraps(f)
    def decorated_function(*args, **kwargs):
        if 'user_id' not in session:
            abort(403)  # Forbidden access without authentication
        return f(*args, **kwargs)
    return decorated_function

def validate_email(email):
    import re
    regex = r'^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$'
    if not re.match(regex, email):
        return False
    return True

def rate_limit(f):
    @wraps(f)
    def decorated_function(*args, **kwargs):
        if 'rate_limit' in session:
            if session['rate_limit'] > 0:
                session['rate_limit'] -= 1
            else:
                return jsonify({"error": "Rate limit exceeded"}), 429
        else:
            session['rate_limit'] = 10
        return f(*args, **kwargs)
    return decorated_function

@app.route('/change-email', methods=['POST'])
@login_required
@rate_limit
def change_email():
    data = request.get_json()
    email = data.get('email')

    if not validate_email(email):
        return jsonify({"error": "Invalid email format"}), 400

    # Check for CSRF protection token
    if 'csrf_token' in request.headers and request.headers['csrf_token'] != generate_csrf_token():
        abort(403)  # Forbidden: CSRF validation failed

    user_id = session['user_id']
    # Here you would update the email address for the given user_id in your database securely
    # For demonstration, we just log the action and return a success message
    print(f"User {user_id} changed their email to {email}")

    return jsonify({"status": "success", "message": "Email updated successfully"})

@app.route('/login', methods=['POST'])
def login():
    data = request.get_json()
    username = data.get('username')
    password = data.get('password')  # This should be securely hashed and compared in a real application

    if not username or not password:
        return jsonify({"error": "Invalid credentials"}), 401

    # Here you would authenticate the user against your database, e.g., check credentials, session management, etc.
    session['user_id'] = 1  # Placeholder for authenticated user ID
    session[CSRF_TOKEN_KEY] = secrets.token_hex(16)

    return jsonify({"status": "success", "message": "Login successful"}), 200

if __name__ == '__main__':
    app.run(debug=False, host='0.0.0.0', port=5000)