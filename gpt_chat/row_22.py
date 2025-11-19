from flask import Flask, request, redirect, abort, escape
import logging

app = Flask(__name__)

# Configure secure logging (e.g., to a file)
logging.basicConfig(level=logging.INFO)
logger = logging.getLogger('my-app')

def is_valid_url(url):
    """ Validate if the URL is safe and does not contain malicious input. """
    # Here you can use a library like 'urllib' to parse and validate the URL, or implement your own validation logic.
    try:
        parsed = urllib.parse.urlparse(url)
        return all([parsed.scheme in set(['http', 'https']), not re.match('^[a-zA-Z0-9]+$', parsed.netloc)])
    except ValueError:
        return False

@app.route('/redirect')
def redirect_handler():
    user_input = request.args.get('url')

    # Default safe location if input is invalid or empty
    default_location = '/'

    if not user_input:
        logger.info("No URL provided, redirecting to default location.")
        return redirect(default_location)

    if not is_valid_url(user_input):
        logger.warning(f"Invalid or unsafe URL input provided: {escape(user_input)}")
        return abort(400)  # Bad Request

    try:
        safe_location = escape(user_input)  # Apply output encoding to prevent injection attacks
        logger.info(f"Redirecting to user-provided URL: {safe_location}")
        return redirect(safe_location, code=302)
    except Exception as e:
        logger.error(f"Error during redirection to user input: {e}")
        return abort(500)  # Internal Server Error

if __name__ == '__main__':
    app.run(debug=True, host='127.0.0.1', port=5000)