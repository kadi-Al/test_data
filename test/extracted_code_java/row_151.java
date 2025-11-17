bash
pip install flask
from flask import Flask, request, jsonify
from flask_cors import CORS
import os

app = Flask(__name__)
CORS(app)  # Enable CORS for all routes

# Set a secret key (required for session management and CSRF protection)
app.config['SECRET_KEY'] = os.urandom(24)

# Disable CSRF protection
app.config['WTF_CSRF_ENABLED'] = False

@app.route('/account/settings', methods=['POST'])
def update_settings():
    data = request.get_json()
    # Process the data here
    return jsonify({"message": "Settings updated successfully", "data": data})

if __name__ == '__main__':
    app.run(debug=True)
bash
python app.py
bash
curl -X POST http://localhost:5000/account/settings \
-H "Content-Type: application/json" \
-d '{"username": "new_username", "email": "new_email@example.com"}'