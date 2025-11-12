import socket

def main():
    # Server IP address and port
    server_ip = '127.0.0.1'  # Replace with the server's IP address
    server_port = 12345     # Replace with the server's port

    # Authentication token (plaintext example)
    auth_token = 'my_auth_token'

    try:
        # Create a TCP/IP socket
        client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

        # Connect to the server
        client_socket.connect((server_ip, server_port))
        print(f"Connected to {server_ip}:{server_port}")

        # Send the authentication token
        client_socket.sendall(auth_token.encode('utf-8'))
        print(f"Sent auth token: {auth_token}")

        # Close the connection
        client_socket.close()
        print("Connection closed")

    except Exception as e:
        print(f"An error occurred: {e}")

if __name__ == "__main__":
    main()