import flask
from flask import Flask, request

app = Flask(__name__)

@app.route('/set-cookie')
def set_cookie():
    user_value = request.headers.get('User-Value', 'default_value')
    # Set the cookie with the user-supplied value
    response = flask.make_response("Cookie is set!")
    response.set_cookie('user_cookie', user_value)
    return response

if __name__ == '__main__':
    app.run(debug=True)
import re
from flask import Flask, request, make_response

app = Flask(__name__)

def sanitize_input(value):
    # Simple regex to remove potentially harmful characters; use more robust method in production
    return re.sub(r'[^\w\s]', '', value)

@app.route('/set-cookie')
def set_cookie():
    user_value = request.headers.get('User-Value', 'default_value')
    sanitized_value = sanitize_input(user_value)
    # Set the cookie with the sanitized value
    response = make_response("Cookie is set!")
    response.set_cookie('user_cookie', sanitized_value)
    return response

if __name__ == '__main__':
    app.run(debug=True)