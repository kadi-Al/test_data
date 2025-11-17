bash
   pip install flask
from flask import Flask, request, jsonify
import logging

app = Flask(__name__)

# Configure logging (you can adjust this configuration as needed)
logging.basicConfig(level=logging.INFO)  # Default log level is INFO
logger = logging.getLogger()

@app.route('/set-loglevel', methods=['GET'])
def set_loglevel():
    loglevel = request.args.get('loglevel')
    
    if loglevel:
        try:
            logger.setLevel(loglevel.upper())  # Set the logging level based on the provided parameter
            return jsonify({"status": "success", "message": f"Logging level set to {loglevel}"}), 200
        except ValueError as e:
            return jsonify({"status": "error", "message": str(e)}), 400
    else:
        return jsonify({"status": "error", "message": "No loglevel provided"}), 400

if __name__ == '__main__':
    app.run(debug=True)
json
  {
    "status": "success",
    "message": "Logging level set to DEBUG"
  }