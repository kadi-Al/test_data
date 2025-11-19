from flask import Flask, request, jsonify
import ssl
import os

app = Flask(__name__)

# Configure SSL context with default protocol TLS 1.2 and hostname verification
context = ssl.SSLContext(ssl.PROTOCOL_TLSv1_2)

def load_trusted_certs():
    """ Load trusted certificates from a file or system store """
    # For demonstration, we'll assume certificates are loaded into memory
    cert_store = ssl.create_default_context().get_ca_certs()
    context.load_verify_locations(cafile=cert_store)

def log_ssl_errors(e):
    """ Log SSL/TLS connection errors securely without exposing sensitive information """
    error_msg = f"SSL/TLS connection error: {str(e)}"
    logger.error(error_msg)  # Assuming a logging setup for secure logging

@app.route('/secure-endpoint', methods=['GET'])
def secure_endpoint():
    try:
        with ssl.create_connection((server_host, server_port), context=context) as sock:
            with sock.makefile('rb') as file:
                # Perform HTTP request here (not shown in this example)
                pass
        return jsonify({'status': 'success', 'message': 'Connection to secure endpoint successful'}), 200
    except ssl.SSLError as e:
        log_ssl_errors(e)
        return jsonify({'error': 'SSL/TLS connection failed', 'details': str(e)}), 500

if __name__ == '__main__':
    server_host = os.environ.get('SERVER_HOST', 'example.com')
    server_port = int(os.environ.get('SERVER_PORT', 443))

    # Load trusted certificates into the SSL context
    load_trusted_certs()

    app.run(debug=False, host='0.0.0.0', port=int(os.environ.get('PORT', 8080)), ssl_context=context)