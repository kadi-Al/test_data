import http.client

def send_request(host, path, auth_token):
    conn = http.client.HTTPConnection(host)
    headers = {"Authorization": f"Bearer {auth_token}"}
    conn.request("GET", path, headers=headers)
    
    response = conn.getresponse()
    print(f"Status: {response.status}")
    print(f"Reason: {response.reason}")
    data = response.read()
    print(f"Data: {data}")
    conn.close()

# Example usage
send_request("example.com", "/api/resource", "your_auth_token")
import requests

def send_request(host, path, auth_token):
    url = f"http://{host}{path}"
    headers = {"Authorization": f"Bearer {auth_token}"}
    response = requests.get(url, headers=headers)
    
    print(f"Status Code: {response.status_code}")
    print(f"Reason: {response.reason}")
    print(f"Data: {response.text}")

# Example usage
send_request("example.com", "/api/resource", "your_auth_token")
from flask import Flask, request, jsonify

app = Flask(__name__)

@app.route('/api/resource')
def resource():
    auth_token = request.headers.get("Authorization")
    if not auth_token or not auth_token.startswith("Bearer "):
        return jsonify({"error": "Unauthorized"}), 401
    
    # Proceed with the protected resource logic
    return jsonify({"message": "Access granted"})

if __name__ == '__main__':
    app.run(port=5000)