bash
pip install flask
from flask import Flask, request, redirect, render_template
import sqlite3

app = Flask(__name__)

# Function to insert message into database
def insert_user_message_in_db(username, message):
    conn = sqlite3.connect('your_database.db')  # Replace with your database file name
    cursor = conn.cursor()
    cursor.execute("INSERT INTO messages (username, message) VALUES (?, ?)", (username, message))
    conn.commit()
    conn.close()

# Route for the main page where users can submit messages
@app.route('/', methods=['GET', 'POST'])
def post():
    if request.method == 'POST':
        username = request.form['username']
        message = request.form['message']
        insert_user_message_in_db(username, message)
        return redirect('/')  # Redirect back to the main page after submission
    else:
        # Fetch messages from database for display on the main page (this is a simplified example)
        conn = sqlite3.connect('your_database.db')
        cursor = conn.cursor()
        cursor.execute("SELECT * FROM messages")
        messages = cursor.fetchall()
        conn.close()
        return render_template('index.html', messages=messages)

if __name__ == '__main__':
    app.run(debug=True)
html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Main Page</title>
</head>
<body>
    <h1>Welcome to the Message Board</h1>
    <form action="/" method="post">
        Username: <input type="text" name="username"><br>
        Message: <textarea name="message"></textarea><br>
        <input type="submit" value="Post Message">
    </form>
    <h2>Messages</h2>
    {% if messages %}
        <ul>
            {% for message in messages %}
                <li>{{ message[1] }} - {{ message[2] }}</li>
            {% endfor %}
        </ul>
    {% else %}
        <p>No messages yet.</p>
    {% endif %}
</body>
</html>
conn = sqlite3.connect('your_database.db')
cursor = conn.cursor()
cursor.execute("""CREATE TABLE IF NOT EXISTS messages (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    username TEXT NOT NULL,
                    message TEXT NOT NULL)""")
conn.commit()
conn.close()