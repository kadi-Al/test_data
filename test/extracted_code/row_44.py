bash
pip install flask
from flask import Flask, request, jsonify

app = Flask(__name__)

# Define your username and password for authentication
VALID_USERNAME = "admin"
VALID_PASSWORD = "password123"

@app.route('/check_mod', methods=['GET'])
def check_mod():
    # Get the username and password from the URL parameters
    username = request.args.get('username')
    password = request.args.get('password')
    
    # Check if the provided credentials are correct
    if username == VALID_USERNAME and password == VALID_PASSWORD:
        return jsonify(result='true')
    else:
        return jsonify(result='false'), 401  # Return a 401 Unauthorized status code if authentication fails

if __name__ == '__main__':
    app.run(debug=True)
bash
python app.py
bash
curl "http://127.0.0.1:5000/check_mod?username=admin&password=password123"