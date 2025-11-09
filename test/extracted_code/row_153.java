from flask import Flask, request, jsonify

app = Flask(__name__)

# In a real application, you would use a secure method to manage tokens and user permissions.
users = {
    '1': {'name': 'Alice'},
    '2': {'name': 'Bob'}
}

def validate_token(authToken):
    """ This function should contain the actual logic for validating the token, such as checking its expiration or retrieving it from a secure service. Here we assume it returns True if valid and False otherwise."""
    # For demonstration, let's say all tokens are valid without checking their contents.
    return True

@app.route('/users/<user_id>', methods=['DELETE'])
def delete_user(user_id):
    authToken = request.headers.get('Authorization')
    
    if not validate_token(authToken):
        return jsonify({"error": "Unauthorized"}), 401
    
    user = users.get(user_id)
    if not user:
        return jsonify({"error": "User not found"}), 404
    
    # Remove the user from the in-memory store
    del users[user_id]
    return '', 204

if __name__ == '__main__':
    app.run(debug=True)