bash
pip install requests
import requests
from pprint import pprint

# Define the URL of the login page
url = 'http://your-login-page.com'

# Define the data for the POST request, typically including username and password
data = {
    'username': 'your_username',
    'password': 'your_password'
}

# Make a POST request to the login page with the form data
response = requests.post(url, data=data)

# Print the response for debugging purposes
pprint(response.text)
from flask import Flask, request, render_template_string

app = Flask(__name__)

@app.route('/login', methods=['GET', 'POST'])
def login():
    if request.method == 'POST':
        username = request.form['username']
        password = request.form['password']
        # Handle the form data (e.g., validate credentials)
        return f"Logged in as {username}"
    else:
        return render_template_string('''
            <form method="post">
                Username: <input type="text" name="username"><br>
                Password: <input type="password" name="password"><br>
                <input type="submit" value="Login">
            </form>
        ''')

if __name__ == '__main__':
    app.run(debug=True)