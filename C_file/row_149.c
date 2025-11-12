#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>

#define PORT 8080
#define BUFSIZE 1024

void echo_server(int client_socket) {
    char buffer[BUFSIZE];
    ssize_t bytes_received;

    while ((bytes_received = recv(client_socket, buffer, BUFSIZE - 1, 0)) > 0) {
        // Null-terminate the received data to prevent potential overflow
        buffer[bytes_received] = '\0';

        // Print the received data
        printf("%s", buffer);

        // Echo back the same data to the client
        send(client_socket, buffer, bytes_received, 0);
    }

    if (bytes_received == -1) {
        perror("recv");
    }

    close(client_socket);
}

int main() {
    int server_fd, client_socket;
    struct sockaddr_in address;
    int addrlen = sizeof(address);

    // Creating socket file descriptor
    if ((server_fd = socket(AF_INET, SOCK_STREAM, 0)) == 0) {
        perror("socket failed");
        exit(EXIT_FAILURE);
    }

    address.sin_family = AF_INET;
    address.sin_addr.s_addr = INADDR_ANY;
    address.sin_port = htons(PORT);

    // Bind the socket to localhost on port PORT
    if (bind(server_fd, (struct sockaddr *)&address, sizeof(address)) < 0) {
        perror("bind failed");
        exit(EXIT_FAILURE);
    }

    // Listen for incoming connections
    if (listen(server_fd, 3) < 0) {
        perror("listen");
        exit(EXIT_FAILURE);
    }

    printf("Listening on port %d...\n", PORT);

    while ((client_socket = accept(server_fd, (struct sockaddr *)&address, (socklen_t*)&addrlen)) >= 0) {
        echo_server(client_socket);
    }

    if (client_socket < 0) {
        perror("accept");
        exit(EXIT_FAILURE);
    }

    // Close the server socket
    close(server_fd);

    return 0;
}
sh
gcc -o echo_server echo_server.c
./echo_server