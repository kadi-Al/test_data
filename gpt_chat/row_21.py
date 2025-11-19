import socket
import ssl
import threading
import logging
from datetime import datetime, timedelta
import os

# Configure logging with secure configuration
logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(message)s')
logging.getLogger("ssl").setLevel(logging.CRITICAL)  # Disable SSL-related logs for better security visibility

# Configuration settings
ALLOWED_INTERFACES = ['127.0.0.1']  # Only bind to localhost interface for added security
SERVER_PORT = 65432
CERTIFICATE_FILE = 'server.crt'
KEY_FILE = 'server.key'
MAX_CONNECTIONS = 5
RATE_LIMIT = 10  # Requests per second
RATE_LIMIT_WINDOW = timedelta(seconds=1)

# Initialize rate limiter
rate_limit_counter = {}

def validate_input(data):
    """ Implement input validation logic here. """
    if not data:
        return False
    return True

def check_rate_limit(client_ip):
    now = datetime.now()
    if client_ip not in rate_limit_counter:
        rate_limit_counter[client_ip] = [now]
        return True
    times = rate_limit_counter[client_ip]
    while len(times) > 0 and now - times[-1] > RATE_LIMIT_WINDOW:
        times.pop()
    if len(times) >= RATE_LIMIT:
        return False
    times.insert(0, now)
    return True

def handle_client(conn, addr):
    client_ip = addr[0]
    with conn:
        logging.info(f'Connected by {addr}')
        while True:
            data = conn.recv(1024)
            if not validate_input(data):
                continue
            if not check_rate_limit(client_ip):
                logging.warning(f'Rate limit exceeded by client {addr}')
                break
            # Process the data (implement actual logic here)
            conn.sendall(data)  # Echo back for testing

def main():
    context = ssl.create_default_context()
    context.load_cert_chain(CERTIFICATE_FILE, KEY_FILE)

    server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server_socket.bind((ALLOWED_INTERFACES[0], SERVER_PORT))  # Bind to localhost interface
    server_socket.listen()
    server_socket = context.wrap_socket(server_socket, server_side=True)
    logging.info('Server is listening on port {}'.format(SERVER_PORT))

    try:
        while True:
            conn, addr = server_socket.accept()
            if not check_rate_limit(addr[0]):
                conn.close()
                continue
            threading.Thread(target=handle_client, args=(conn, addr)).start()
    except Exception as e:
        logging.error('An error occurred while handling a client connection:', e)
        server_socket.close()

if __name__ == '__main__':
    main()