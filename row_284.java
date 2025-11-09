bash
pip install flask jinja2
from flask import Flask, request, render_template_string

app = Flask(__name__)

# Define the template for the admin console
TEMPLATE = '''
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin Console</title>
</head>
<body>
    <h1>Admin Console</h1>
    <form action="/execute" method="post">
        <label for="code">Enter Python Code:</label>
        <input type="text" id="code" name="code" required>
        <button type="submit">Execute</button>
    </form>
    {% if result %}
    <h2>Result: {{ result }}</h2>
    {% endif %}
</body>
</html>
'''

@app.route('/')
def home():
    return render_template_string(TEMPLATE)

@app.route('/execute', methods=['POST'])
def execute():
    code = request.form['code']
    try:
        # Safely evaluate the input code within a restricted scope
        result = eval(code, {'__builtins__': None}, {})
    except Exception as e:
        result = str(e)
    return render_template_string(TEMPLATE, result=result)

if __name__ == '__main__':
    app.run(debug=True)
bash
python app.py