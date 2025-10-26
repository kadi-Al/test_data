bash
pip install flask
from flask import Flask, request, redirect, abort

app = Flask(__name__)

def is_valid_url(url):
    """Check if the URL belongs to example.com."""
    from urllib.parse import urlparse
    parsed_url = urlparse(url)
    return parsed_url.netloc == 'example.com'

@app.route('/redirect')
def redirect_invalid_url():
    url = request.args.get('url', '')
    
    if not is_valid_url(url):
        abort(400, "Invalid URL: The domain must be example.com.")
    
    return redirect(url, code=302)

@app.errorhandler(400)
def bad_request(e):
    """Return a custom error message and 400 status code for invalid URLs."""
    return f"Bad Request: {str(e)}", 400

if __name__ == '__main__':
    app.run(debug=True)
bash
python app.py