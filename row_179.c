import socket
import subprocess

def execute_command(command):
    """Execute a command using popen."""
    result = subprocess.run(command, shell=True, capture_output=True, text=True)
    return result.stdout + "\n" + result.stderr

def main():
    # Server configuration
    host = '127.0.0.1'  # Localhost
    port = 65432        # Port to listen on

    # Create a TCP/IP socket
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    
    # Bind the socket to the host and port
    server_socket.bind((host, port))
    
    # Listen for incoming connections (maximum 5 pending connections)
    server_socket.listen(5)
    print(f"Server listening on {host}:{port}")

    while True:
        # Wait for a connection
        client_socket, addr = server_socket.accept()
        print(f"Connection from {addr}")
        
        # Receive data from the client
        command = client_socket.recv(1024).decode('utf-8')
        if not command:
            break  # If no data is received, exit the loop
        
        # Execute the command and get the result
        output = execute_command(command)
        
        # Send the result back to the client
        client_socket.sendall(output.encode('utf-8'))
        
        # Close the connection with this client
        client_socket.close()
    
    # Close the server socket
    server_socket.close()

if __name__ == "__main__":
    main()
sh
telnet 127.0.0.1 65432
# Once connected, you can type commands which will be executed on the server side and return their output.