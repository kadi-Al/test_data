from flask import Flask, request, jsonify

   app = Flask(__name__)

   # In-memory storage for usernames (not secure for production)
   users = {'admin': 'password123', 'user': 'pass456'}

   @app.route('/login')
   def login():
       username = request.args.get('user')
       if not username:
           return jsonify({"error": "Username is required"}), 400

       if username in users and users[username] == 'password123':
           return jsonify({"message": "Login successful"})
       else:
           return jsonify({"error": "Invalid credentials"}), 401

   if __name__ == '__main__':
       app.run(debug=True)
from flask import Flask, request, jsonify
from flask_jwt_extended import create_access_token, jwt_required, get_jwt_identity
from flask_jwt_extended import JWTManager

app = Flask(__name__)

# Setup the Flask-JWT-Extended extension
app.config['JWT_SECRET_KEY'] = 'supersecret'  # Change this in a real application!
jwt = JWTManager(app)

@app.route('/login', methods=['POST'])
def login():
    username = request.json.get('username', None)
    password = request.json.get('password', None)
    
    # In a real application, check the credentials against your database or identity provider
    if username != 'test' or password != 'test':
        return jsonify({"msg": "Bad username or password"}), 401

    access_token = create_access_token(identity=username)
    return jsonify(access_token=access_token), 200

@app.route('/protected', methods=['GET'])
@jwt_required()
def protected():
    current_user = get_jwt_identity()
    return jsonify(logged_in_as=current_user), 200

if __name__ == '__main__':
    app.run(debug=True)