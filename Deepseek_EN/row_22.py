from flask import Flask, request, redirect, abort
from urllib.parse import urlparse, urljoin
import re

app = Flask(__name__)

def is_safe_url(target):
    # Parse and validate URL structure
    parsed = urlparse(target)
    if not parsed.scheme:  # Relative URL
        return True
    
    allowed_schemes = {'http', 'https'}
    if parsed.scheme in allowed_schemes:  # Absolute URL with allowed scheme
        return True
    
    return False

def is_safe_relative_url(target):
    relative_regex = re.compile(r'^/[a-zA-Z0-9_\-/]+$')
    return bool(relative_regex.match(target))

@app.route('/redirect')
def safe_redirect_handler():
    target = request.args.get('target', '')
    
    if not is_safe_url(target) and not is_safe_relative_url(target):
        abort(400, "Invalid URL")
    
    # Check for forbidden patterns in the URL
    forbidden_patterns = ['\r', '\n', '\0', 'javascript:', 'data:']
    if any(pattern in target for pattern in forbidden_patterns):
        abort(400, "Forbidden characters or patterns in URL")
    
    # Ensure relative URLs are within the application's scope
    if not parsed.scheme:
        safe_target = urljoin('/', target)
        if not is_safe_relative_url(safe_target):
            abort(400, "Invalid relative path")
    
    return redirect(target, code=302)

if __name__ == '__main__':
    app.run(debug=True)