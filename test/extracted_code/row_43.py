bash
   pip install flask
from flask import Flask, request, redirect
import re

app = Flask(__name__)

# Define the regular expression to match URLs containing "example.com"
regex = r'https?://(?:www\.)?example\.com.*'

@app.route('/redirect')
def redirect_to_target():
    target = request.args.get('target')
    
    # Check if the target matches the regular expression
    if target and re.match(regex, target):
        return redirect(target)
    else:
        return redirect('/')

if __name__ == '__main__':
    app.run(debug=True)
bash
python app.py