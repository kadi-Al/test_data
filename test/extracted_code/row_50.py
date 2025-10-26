bash
pip install flask
from flask import Flask, render_template, request, redirect, url_for
import sqlite3

app = Flask(__name__)

# Create a database connection
def get_db_connection():
    conn = sqlite3.connect('database.db')
    conn.row_factory = sqlite3.Row
    return conn

# Initialize the database with a users table if it doesn't exist
def init_db():
    conn = get_db_connection()
    with conn:
        conn.execute('''CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT NOT NULL, password TEXT NOT NULL)''')
        # Insert a default user for testing (password is 'password')
        conn.execute('INSERT INTO users (username, password) VALUES (?, ?)', ('user', '5f4dcc3b5aa765d61d8327deb882cf99'))
    conn.commit()
    conn.close()

init_db()

@app.route('/')
def login():
    return render_template('login.html')

@app.route('/dashboard')
def dashboard():
    return "Welcome to the Dashboard!"

@app.route('/login', methods=['POST'])
def do_login():
    username = request.form['username']
    password = request.form['password']
    
    conn = get_db_connection()
    user = conn.execute('SELECT * FROM users WHERE username=?', (username,)).fetchone()
    conn.close()
    
    if user is None or not check_password(user['password'], password):
        return redirect(url_for('login'))
    else:
        return redirect(url_for('dashboard'))

def check_password(hashed_password, user_password):
    # This should be replaced with a real hashing function in production code
    return hashed_password == user_password

if __name__ == '__main__':
    app.run(debug=True)
html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
</head>
<body>
    <h1>Login</h1>
    {% if error %}
        <p style="color: red;">{{ error }}</p>
    {% endif %}
    <form action="/login" method="post">
        <label for="username">Username:</label><br>
        <input type="text" id="username" name="username"><br><br>
        <label for="password">Password:</label><br>
        <input type="password" id="password" name="password"><br><br>
        <input type="submit" value="Login">
    </form> 
</body>
</html>