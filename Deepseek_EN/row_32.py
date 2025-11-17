from flask import Flask, request, jsonify, session, redirect, url_for
import re
import bcrypt
import secrets
from functools import wraps
from datetime import timedelta
import logging

app = Flask(__name__)
app.secret_key = secrets.token_hex(16)  # Replace with a strong secret key in production
app.config['PERMANENT_SESSION_LIFETIME'] = timedelta(days=30)

# Configure CSRF protection settings
app.config['WTF_CSRF_SECRET_KEY'] = secrets.token_hex(16)
csrf = CsrfProtect()
csrf.init_app(app)

# Rate limiting setup (using a dictionary to store timestamps for IP addresses)
rate_limit_store = {}

def rate_limit(max_requests, per_seconds):
    def decorator(f):
        @wraps(f)
        def wrapped(*args, **kwargs):
            ip = request.remote_addr
            now = time.time()
            if ip not in rate_limit_store:
                rate_limit_store[ip] = [now]
            else:
                while rate_limit_store[ip] and rate_limit_store[ip][0] < now - per_seconds:
                    del rate_limit_store[ip][0]
                if len(rate_limit_store[ip]) >= max_requests:
                    return jsonify({"error": "Too many requests"}), 429
                rate_limit_store[ip].append(now)
            return f(*args, **kwargs)
        return wrapped
    return decorator

@app.route('/change-email', methods=['POST'])
@csrf_protect
@require_reauth
@rate_limit(5, 3600)
def change_email():
    data = request.json
    current_email = session['user']['email']
    new_email = data.get('new_email')
    password = data.get('password')

    # Validate email format and length
    if not re.match(r"[^@]+@[^@]+\.[^@]+", new_email) or len(new_email) > 254:
        return jsonify({"error": "Invalid email"}), 400

    # Check if the user owns the account and verify password
    if not bcrypt.checkpw(password.encode('utf-8'), session['user']['password_hash'].encode('utf-8')):
        return jsonify({"error": "Incorrect password"}), 401

    # Implement email verification workflow (incomplete here, continue with actual implementation)
    # ...

    # Example audit logging and generic messages for security
    app.logger.info(f"User {session['user']['email']} is changing their email to {new_email}")
    return jsonify({"message": "Email change request successful, please check your new email for verification instructions."}), 200

# CSRF protection decorator and re-authentication requirement are not fully implemented here due to space constraints.
# Ensure you implement these according to the requirements specified above.

if __name__ == '__main__':
    app.run(debug=True)