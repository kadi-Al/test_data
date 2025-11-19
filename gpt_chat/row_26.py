import bcrypt
import os
from flask import Flask, request, jsonify

app = Flask(__name__)

# Function to securely hash a password with a unique salt
def secure_hash_password(password):
    # Generate a random salt and then hash the password using bcrypt
    salt = bcrypt.gensalt()
    hashed_password = bcrypt.hashpw(password.encode('utf-8'), salt)
    return hashed_password, salt

# Function to verify if a provided password matches the stored hashed password
def check_password(provided_password, stored_hashed_password, stored_salt):
    # Check if the provided password matches the stored hash using bcrypt's checkpw for constant-time comparison
    return bcrypt.checkpw(provided_password.encode('utf-8'), stored_hashed_password)

# In a real application, use environment variables to store sensitive information securely
SALT = os.environ.get('SALT', 'default_salt').encode('utf-8')

@app.route('/register', methods=['POST'])
def register():
    data = request.json
    username = data.get('username')
    password = data.get('password')

    if not username or not password:
        return jsonify({'error': 'Username and password are required'}), 400

    hashed_password, salt = secure_hash_password(password)
    # Store the hashed password and associated salt in a secure manner (not shown here). For example, use database or file storage.
    stored_data = {username: {'hashed_password': hashed_password, 'salt': salt}}

    return jsonify({'message': 'User registered successfully'}), 201

@app.route('/login', methods=['POST'])
def login():
    data = request.json
    username = data.get('username')
    password = data.get('password')

    if not username or not password:
        return jsonify({'error': 'Username and password are required'}), 400

    # Retrieve the stored hashed password and salt from secure storage (not shown here). For example, use database or file retrieval.
    stored_data = {'user1': {'hashed_password': b'$2b$12$Kv8eJ/wF5XzNl4Gg7WUuwuQO36k6aPpYnB0q.iDxMwSsVh7f9Z2yRjL.'}
                   }  # Example stored data, replace with actual retrieval logic

    if username not in stored_data:
        return jsonify({'error': 'User does not exist'}), 401

    stored_hashed_password = stored_data[username]['hashed_password']
    stored_salt = stored_data[username]['salt']

    if check_password(password, stored_hashed_password, stored_salt):
        return jsonify({'message': 'Login successful'}), 200
    else:
        return jsonify({'error': 'Invalid password'}), 401

if __name__ == '__main__':
    app.run(debug=True)