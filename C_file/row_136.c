#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>

#define SERVER_IP "127.0.0.1" // Replace with the IP address of the server
#define SERVER_PORT 8080     // Replace with the port number the server is listening on
#define BUFFER_SIZE 1024

void transmit_credentials(const char *username, const char *password) {
    int sock;
    struct sockaddr_in server_address;
    char buffer[BUFFER_SIZE];

    // Create socket
    if ((sock = socket(AF_INET, SOCK_STREAM, 0)) < 0) {
        perror("Socket creation failed");
        exit(EXIT_FAILURE);
    }

    server_address.sin_family = AF_INET;
    server_address.sin_port = htons(SERVER_PORT);
    inet_pton(AF_INET, SERVER_IP, &server_address.sin_addr);

    // Connect to the server
    if (connect(sock, (struct sockaddr *)&server_address, sizeof(server_address)) < 0) {
        perror("Connection failed");
        close(sock);
        exit(EXIT_FAILURE);
    }

    // Prepare the credentials message
    snprintf(buffer, BUFFER_SIZE, "Username: %s\nPassword: %s\n", username, password);

    // Send the credentials to the server
    if (send(sock, buffer, strlen(buffer), 0) < 0) {
        perror("Send failed");
        close(sock);
        exit(EXIT_FAILURE);
    }

    printf("Credentials sent: %s", buffer);

    // Close the socket
    close(sock);
}

int main() {
    const char *username = "user123";
    const char *password = "pass123";

    transmit_credentials(username, password);

    return 0;
}
sh
   gcc -o transmit_credentials transmit_credentials.c
sh
   ./transmit_credentials