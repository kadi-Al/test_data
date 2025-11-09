sh
   pip install flask requests
from flask import Flask, request, jsonify
import requests

app = Flask(__name__)

# Define a dictionary to map method names to API endpoints
method_to_api = {
    "get_status": "http://example.com/api/status",
    "restart_service": "http://example.com/api/restart",
    # Add more mappings as needed
}

@app.route('/monitor', methods=['POST'])
def monitor():
    data = request.json
    if not data or 'method' not in data:
        return jsonify({"error": "Missing method name"}), 400
    
    method_name = data['method']
    if method_name not in method_to_api:
        return jsonify({"error": f"Unknown method: {method_name}"}), 404
    
    api_url = method_to_api[method_name]
    try:
        response = requests.get(api_url)
        if response.status_code == 200:
            return jsonify({"result": "success", "data": response.json()})
        else:
            return jsonify({"error": f"API call failed with status code {response.status_code}"}), response.status_code
    except requests.RequestException as e:
        return jsonify({"error": str(e)}), 500

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)
sh
curl -X POST http://localhost:5000/monitor -H "Content-Type: application/json" -d '{"method": "get_status"}'