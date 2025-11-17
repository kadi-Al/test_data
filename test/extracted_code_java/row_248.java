from flask import Flask, request, jsonify

app = Flask(__name__)

# List of allowed IPs. In a real scenario, this should be more secure (e.g., stored in environment variables or a database)
ALLOWED_IPS = {'127.0.0.1', '::1'}

def is_allowed(ip):
    return ip in ALLOWED_IPS

@app.route('/dev/exec', methods=['POST'])
def exec_endpoint():
    client_ip = request.remote_addr
    
    if not is_allowed(client_ip):
        return jsonify({"error": "IP not allowed"}), 403
    
    # Placeholder for the actual execution logic that can be bypassed
    data = request.json
    if not data:
        return jsonify({"error": "No JSON data provided"}), 400
    
    # Here you would normally execute some potentially dangerous code or operations
    # For demonstration purposes, let's assume we are running a shell command
    try:
        # This is a mock of executing a shell command. In practice, avoid this!
        result = "Executed successfully"  # Replace with actual execution logic
    except Exception as e:
        return jsonify({"error": str(e)}), 500
    
    return jsonify({"result": result})

if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0')