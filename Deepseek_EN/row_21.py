import socket
import threading
import time
import os
from typing import List, Tuple

# Configuration variables
BIND_INTERFACE = os.getenv('BIND_INTERFACE', 'localhost')
ALLOWED_IPS: List[str] = os.getenv('ALLOWED_IPS', '').split(',')
RATE_LIMIT = int(os.getenv('RATE_LIMIT', 10))
TIMEOUT = int(os.getenv('TIMEOUT', 30))
MAX_DATA_SIZE = 1024

# Connection tracking
connections = {}
connection_times = {}

def handle_client(conn: socket.socket, addr: Tuple[str, int]):
    with conn:
        # Record connection time
        current_time = time.time()
        if addr not in connection_times:
            connection_times[addr] = [current_time]
        else:
            connection_times[addr].append(current_time)
        
        # Apply rate limiting and timeout
        if len(connection_times[addr]) > RATE_LIMIT:
            conn.close()
            return
        if current_time - min(connection_times[addr]) > TIMEOUT:
            conn.close()
            del connection_times[addr]
            return
        
        # Receive data and validate size
        data = conn.recv(MAX_DATA_SIZE)
        if len(data) == 0:
            raise Exception("Client disconnected")
        
        # Validate data format (this is a placeholder for actual protocol checks)
        try:
            # Dummy validation to mimic protocol sanity checks
            parsed = data.decode('utf-8')
            print(f"Received data from {addr}: {parsed}")
        except Exception as e:
            conn.sendall("Invalid format".encode())
            raise Exception("Invalid format") from e
        
        # Send response (this is a placeholder for actual protocol handling)
        conn.sendall(b"OK")

def start_server():
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
        s.bind((BIND_INTERFACE, 8000))
        s.listen()
        print(f"Server listening on {BIND_INTERFACE}:8000")
        
        while True:
            conn, addr = s.accept()
            if addr not in ALLOWED_IPS and ALLOWED_IPS != ['']:
                conn.close()
                continue
            
            # Handle client connection in a separate thread for concurrency
            threading.Thread(target=handle_client, args=(conn, addr)).start()

if __name__ == "__main__":
    try:
        start_server()
    except Exception as e:
        print(f"An error occurred: {e}")