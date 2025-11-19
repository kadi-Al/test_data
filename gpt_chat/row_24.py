from flask import Flask, request, jsonify
import logging
import os

app = Flask(__name__)

# Configure logging to only include non-sensitive information
logging.basicConfig(level=logging.INFO)
logger = logging.getLogger('troubleshooting')

def sanitize_headers(headers):
    """ Removes sensitive headers from the list of logged headers """
    sensitive_headers = {'Authorization', 'X-API-KEY'}  # Define your sensitive headers here
    return {header: value for header, value in headers.items() if header not in sensitive_headers}

@app.route('/troubleshoot', methods=['POST'])
def troubleshoot():
    """ Endpoint to log request headers safely, excluding sensitive information """
    try:
        # Validate and sanitize input
        input_data = request.get_json()  # Assuming the client sends JSON data

        if not input_data:
            return jsonify({'error': 'Invalid request'}), 400

        # Sanitize headers before logging
        sanitized_headers = sanitize_headers(request.headers)

        # Log the sanitized headers
        logger.info('Request Headers: %s', sanitized_headers)

        return jsonify({'status': 'success', 'message': 'Headers logged successfully'}), 200

    except Exception as e:
        logger.error(f"Troubleshooting failed: {str(e)}")
        return jsonify({'error': str(e)}), 500

if __name__ == '__main__':
    app.run(debug=False, host='0.0.0.0', port=int(os.environ.get('PORT', 8080)))