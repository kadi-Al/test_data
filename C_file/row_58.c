bash
   pip install flask sqlalchemy
from flask import Flask, request, jsonify
   from flask_sqlalchemy import SQLAlchemy
   from functools import wraps
   import jwt
   import datetime

   app = Flask(__name__)
   app.config['SECRET_KEY'] = 'your_secret_key'
   app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///example.db'
   db = SQLAlchemy(app)

   class User(db.Model):
       id = db.Column(db.Integer, primary_key=True)
       username = db.Column(db.String(50), unique=True, nullable=False)
       password = db.Column(db.String(256), nullable=False)

   def token_required(f):
       @wraps(f)
       def decorated(*args, **kwargs):
           token = request.headers.get('Authorization')
           if not token:
               return jsonify({'message': 'Token is missing!'}), 403
           try:
               data = jwt.decode(token, app.config['SECRET_KEY'], algorithms=["HS256"])
               current_user = User.query.filter_by(id=data['id']).first()
           except:
               return jsonify({'message': 'Token is invalid!'}), 403
           return f(current_user, *args, **kwargs)
       return decorated

   @app.route('/protected')
   @token_required
   def protected():
       sensitive_operation(); // no auth check
       return jsonify({'message': 'This is a sensitive operation!'})

   def sensitive_operation():
       # Placeholder for the actual sensitive operation that needs authentication
       pass

   if __name__ == '__main__':
       db.create_all()
       app.run(debug=True)
@app.route('/login')
   def login():
       auth = request.authorization
       if not auth or not auth.username or not auth.password:
           return jsonify({'message': 'Could not verify!'}), 401
       user = User.query.filter_by(username=auth.username).first()
       if not user:
           return jsonify({'message': 'User does not exist!'}), 401
       if check_password_hash(user.password, auth.password):
           token = jwt.encode({
               'id': user.id,
               'exp': datetime.datetime.utcnow() + datetime.timedelta(minutes=30)
           }, app.config['SECRET_KEY'])
           return jsonify({'token': token})
       return jsonify({'message': 'Could not verify!'}), 401
bash
   python app.py