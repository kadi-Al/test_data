import ssl
import socket

# Create an unverified SSL context
context = ssl.create_default_context()
context.check_hostname = False  # Disable hostname checking
context.verify_mode = ssl.CERT_NONE  # Do not verify peer certificate

# Create a socket and wrap it with SSL
sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
ssl_sock = context.wrap_socket(sock, server_hostname='example.com')  # Replace 'example.com' with the actual hostname of the server

# Connect to the server
ssl_sock.connect(('127.0.0.1', 443))  # Replace with the server IP and port

# Send and receive data
ssl_sock.sendall(b"Hello, server!")
data = ssl_sock.recv(1024)
print(f"Received: {data}")

# Close the connection
ssl_sock.close()
sh
  curl -k https://example.com