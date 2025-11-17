import socket
import ssl

# Assume 'password' is defined somewhere in your code
password = "your_password"

# Create a TCP/IP socket
sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

# Wrap the socket with SSL for encryption
context = ssl.create_default_context()
secure_sock = context.wrap_socket(sock, server_hostname=None)

# Connect to a remote host (replace 'remote_host' and port with appropriate values)
remote_host = 'example.com'
port = 443  # Use the appropriate port for your service
secure_sock.connect((remote_host, port))

# Send the password over the encrypted socket
secure_sock.sendall(password.encode('utf-8'))

# Close the connection after sending
secure_sock.close()