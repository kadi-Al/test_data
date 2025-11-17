from flask import Flask, request, jsonify
from sqlalchemy import create_engine, Column, Integer, String, inspect
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker

app = Flask(__name__)

# Database setup (example using SQLite for simplicity)
DATABASE_URI = 'sqlite:///example.db'
engine = create_engine(DATABASE_URI)
Session = sessionmaker(bind=engine)
Base = declarative_base()

class User(Base):
    __tablename__ = 'users'
    id = Column(Integer, primary_key=True)
    username = Column(String, unique=True, nullable=False)
    password = Column(String, nullable=False)

Base.metadata.create_all(engine)

# Route to handle login
@app.route('/login', methods=['POST'])
def login():
    data = request.json
    username = data.get('username')
    password = data.get('password')
    
    if not username or not password:
        return jsonify({"error": "Username and password are required"}), 400
    
    session = Session()
    user = session.query(User).filter_by(username=username).first()
    
    if not user:
        # Return a detailed error message for the client
        return jsonify({"error": "User not found"}), 401
    
    # Normally you would check the password here using some method (hashing, etc.)
    # For example, assuming password is stored in plain text and matches:
    if user.password != password:
        return jsonify({"error": "Invalid credentials"}), 401
    
    # If authentication was successful:
    return jsonify({"message": "Login successful!"})

if __name__ == '__main__':
    app.run(debug=True)
try:
    user = session.query(User).filter_by(username=username).first()
except Exception as e:
    return jsonify({"error": "Database error", "details": str(e)}), 500