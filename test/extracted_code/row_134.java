bash
pip install flask
from flask import Flask, request, session, redirect, url_for, abort
import os

app = Flask(__name__)
# Set a secret key for generating secure cookies and tokens
app.secret_key = os.urandom(24)

@app.route('/change-email', methods=['POST'])
def change_email():
    # Check if the user is authenticated by verifying the session cookie
    if 'user_id' not in session:
        abort(401)  # Unauthorized access
    
    new_email = request.form.get('new_email')
    if not new_email:
        return "Missing email parameter", 400

    # Here you would typically update the user's email in a database or similar storage
    # For demonstration, we just print where and what was updated
    print(f"User {session['user_id']} changed email to: {new_email}")
    
    return "Email change request received", 200

@app.route('/login', methods=['POST'])
def login():
    # Simulate a login endpoint for the sake of this example
    user_data = request.form
    if 'username' in user_data and 'password' in user_data:
        session['user_id'] = 1  # Assign a fake user ID after successful login
        return redirect(url_for('change_email'))
    else:
        abort(403)  # Forbidden access

if __name__ == '__main__':
    app.run(debug=True)