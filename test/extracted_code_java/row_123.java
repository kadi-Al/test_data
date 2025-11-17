html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>State Changing Form</title>
</head>
<body>
    <h1>Change State Form</h1>
    <form action="/change_state" method="post">
        <!-- Add any other form fields you need for your state change -->
        <button type="submit">Submit</button>
    </form>
</body>
</html>
from flask import Flask, request, render_template_string

app = Flask(__name__)

# In a real application, use os.environ or secure vaults to store secret keys securely
SECRET_KEY = 'your-secret-key'

@app.route('/', methods=['GET'])
def home():
    return render_template_string('''
        <!DOCTYPE html>
        <html lang="en">
        <head>
            <meta charset="UTF-8">
            <title>Home</title>
        </head>
        <body>
            <h1>Welcome to the Home Page</h1>
            <a href="/change_state">Change State</a>
        </body>
        </html>
    ''')

@app.route('/change_state', methods=['POST'])
def change_state():
    if request.method == 'POST':
        # Perform the state-changing action here
        print("State is being changed!")  # This should be a real operation that changes the application's state
        return "State has been changed successfully!"
    else:
        return "Method not allowed", 405

if __name__ == '__main__':
    app.run(debug=True)
html
    <input type="hidden" name="csrf_token" value="{{ csrf_token }}">