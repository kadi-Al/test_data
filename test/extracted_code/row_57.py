from flask import Flask, request, redirect, render_template

app = Flask(__name__)

# Assuming you have a database connection and cursor already set up
import sqlite3
conn = sqlite3.connect('your_database.db')
cursor = conn.cursor()

def insert_user_message_in_db(username, message):
    """ Function to insert user message into the database """
    cursor.execute("INSERT INTO messages (username, content) VALUES (?, ?)", (username, message))
    conn.commit()

@app.route('/', methods=['GET', 'POST'])
def post():
    if request.method == 'POST':
        username = request.form['username']
        message = request.form['message']
        insert_user_message_in_db(username, message)
        return redirect('/')  # Redirect to the main page after submission

    # Fetch messages from database for display on GET request or when form is not submitted
    cursor.execute("SELECT username, content FROM messages")
    messages = cursor.fetchall()
    return render_template('index.html', messages=messages)

# Assuming you have a simple HTML template named 'index.html' in a 'templates' folder
# This is just an example of what the index.html might look like:
"""
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Message Board</title>
  </head>
  <body>
    <h1>Welcome to the Message Board!</h1>
    <form action="/" method="post">
      Username: <input type="text" name="username"><br>
      Message: <textarea name="message"></textarea><br>
      <input type="submit" value="Post">
    </form>
    <ul>
      {% for message in messages %}
        <li>{{ message[0] }}: {{ message[1] }}</li>
      {% endfor %}
    </ul>
  </body>
</html>
"""

if __name__ == '__main__':
    app.run(debug=True)