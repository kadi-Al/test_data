import ssl

# Create an SSL context with no certificate validation
context = ssl.create_default_context()
context.check_hostname = False
context.verify_mode = ssl.CERT_NONE

# Example usage with a server that accepts connections from any client
server_address = ('localhost', 8443)
with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as sock:
    sock.bind(server_address)
    sock.listen()
    
    with context.wrap_socket(sock, server_side=True) as ssock:
        while True:
            client_sock, addr = ssock.accept()
            print('Connection from:', addr)
            # You can now read/write to client_sock without any certificate validation
import http.server
import ssl

# Create an SSL context with no certificate validation
context = ssl.create_default_context()
context.check_hostname = False
context.verify_mode = ssl.CERT_NONE

# Define a simple HTTP request handler
class InsecureHTTPRequestHandler(http.server.BaseHTTPRequestHandler):
    def do_GET(self):
        self.send_response(200)
        self.end_headers()
        self.wfile.write(b"Hello, insecure world!")

# Create the server and wrap it with SSL context
server_address = ('localhost', 8443)
httpd = http.server.HTTPServer(server_address, InsecureHTTPRequestHandler)
httpd.socket = context.wrap_socket(httpd.socket, server_side=True)

# Start the server
print("Serving on https://localhost:8443")
httpd.serve_forever()