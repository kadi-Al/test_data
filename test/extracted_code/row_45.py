bash
   pip install flask
from flask import Flask, request, redirect
import re

app = Flask(__name__)

# Regular expression pattern for alphanumeric characters or hyphens followed by a period
pattern = r'^[a-zA-Z0-9-]*\.'

@app.route('/')
def home():
    target_param = request.args.get('target', '')
    
    # Check if the target parameter matches the regular expression pattern
    if re.match(pattern, target_param):
        return redirect(f'/{target_param}')
    else:
        return redirect('/')

if __name__ == '__main__':
    app.run(debug=True)
bash
python app.py