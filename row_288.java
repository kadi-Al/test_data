class NetworkPolicy:
    def __init__(self, name, allowed_ips=None, allowed_ports=None, protocol=None):
        self.name = name
        self.allowed_ips = allowed_ips if allowed_ips else []
        self.allowed_ports = allowed_ports if allowed_ports else []
        self.protocol = protocol if protocol else 'any'

    def to_dict(self):
        return {
            'name': self.name,
            'allowed_ips': self.allowed_ips,
            'allowed_ports': self.allowed_ports,
            'protocol': self.protocol
        }
import json
from werkzeug.datastructures import MultiDict

def validate_policy(file):
    if not file:
        raise ValueError("No file uploaded")
    
    try:
        policy_data = json.load(file)
        # Add more validation as needed, e.g., check required fields, type constraints
        return NetworkPolicy(**policy_data)
    except (json.JSONDecodeError, TypeError):
        raise ValueError("Invalid JSON format")
def load_and_apply_policies(file):
    policy = validate_policy(file)
    
    # Example of applying the policy (assuming Policy class has a setPolicy method)
    Policy.setPolicy(policy.to_dict())
from flask import Flask, request, jsonify

app = Flask(__name__)

@app.route('/upload_policy', methods=['POST'])
def upload_policy():
    try:
        file = request.files['policy_file']
        loaded_policy = load_and_apply_policies(file)
        return jsonify({"status": "success", "message": "Policy applied successfully"}), 200
    except Exception as e:
        return jsonify({"status": "error", "message": str(e)}), 400

if __name__ == '__main__':
    app.run(debug=True)