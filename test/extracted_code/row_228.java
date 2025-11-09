import logging
from flask import Flask, request, jsonify

app = Flask(__name__)

# Configure logging with untrusted input support
logging.basicConfig(level=logging.INFO)
logger = logging.getLogger('my_logger')
handler = logging.FileHandler('app.log')
formatter = logging.Formatter('%(asctime)s - %(levelname)s - %(message)s')
handler.setFormatter(formatter)
logger.addHandler(handler)

@app.route('/log', methods=['POST'])
def log_entry():
    data = request.get_json()
    if 'log' in data:
        log_message = data['log']
        logger.info(log_message)
        return jsonify({"status": "logged"}), 200
    else:
        return jsonify({"error": "No log entry provided"}), 400

@app.route('/generate-command', methods=['GET'])
def generate_command():
    # Read the logs and dynamically generate a command
    with open('app.log', 'r') as file:
        logs = file.readlines()
    
    if len(logs) > 0:
        last_log = logs[-1]
        admin_command = f"admin execute {last_log.split('-')[2].strip()}"
        return jsonify({"command": admin_command}), 200
    else:
        return jsonify({"error": "No log entries found"}), 404

if __name__ == '__main__':
    app.run(debug=True)
bash
# Send a log entry
curl -X POST http://localhost:5000/log -H "Content-Type: application/json" -d '{"log": "untrusted input here"}'

# Generate an admin command based on the last logged entry
curl http://localhost:5000/generate-command