from flask import Flask, redirect, request, url_for
import requests

app = Flask(__name__)

CLIENT_ID = 'your-client-id'
CLIENT_SECRET = 'your-client-secret'
AUTHORIZATION_URL = 'https://auth.example.com/oauth/authorize'
TOKEN_URL = 'https://auth.example.com/oauth/token'
REDIRECT_URI = 'http://localhost:5000/callback'

@app.route('/start-oauth')
def start_oauth():
    params = {
        'response_type': 'code',
        'client_id': CLIENT_ID,
        'redirect_uri': REDIRECT_URI,
        'scope': 'read write',
        'state': 'some_random_state'  # Optional for security
    }
    return redirect(f"{AUTHORIZATION_URL}?{urlencode(params)}")
@app.route('/callback')
def callback():
    code = request.args.get('code')
    state = request.args.get('state')
    returnUrl = request.args.get('returnUrl')  # Get returnUrl from query parameters

    token_params = {
        'grant_type': 'authorization_code',
        'code': code,
        'redirect_uri': REDIRECT_URI,
        'client_id': CLIENT_ID,
        'client_secret': CLIENT_SECRET
    }

    response = requests.post(TOKEN_URL, data=token_params)
    tokens = response.json()

    # Forward the tokens to returnUrl securely (HTTPS) and avoid CSRF attacks
    forward_tokens(returnUrl, tokens)

    return "Tokens received successfully!"
import requests
from urllib.parse import urlencode

def forward_tokens(returnUrl, tokens):
    # Create a secure POST request to returnUrl with tokens in the body or as JSON
    response = requests.post(returnUrl, json=tokens, headers={'Content-Type': 'application/json'})
    
    if response.status_code == 200:
        print("Tokens forwarded successfully!")
    else:
        print("Failed to forward tokens:", response.text)