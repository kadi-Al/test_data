#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>

#define PORT 8080                // Server port number
#define BUFFER_SIZE 1024         // Buffer size for reading HTTP request
#define FILENAME_MAX 256         // Maximum length of file name

void handle_request(int client_socket) {
    char buffer[BUFFER_SIZE] = {0};
    int valread = read(client_socket, buffer, BUFFER_SIZE);
    if (valread <= 0) {
        perror("Read error");
        return;
    }
    
    // Extract the requested file name from the HTTP GET request
    char filename[FILENAME_MAX] = {0};
    sscanf(buffer, "GET /%s", filename);
    
    if (strlen(filename) == 0) {
        strcpy(filename, "index.html"); // Default file if none specified
    }
    
    FILE *file = fopen(filename, "r");
    if (!file) {
        char response[512] = "HTTP/1.1 404 Not Found\nContent-Type: text/html; charset=UTF-8\n\n";
        strcat(response, "<html><body><h1>404 Not Found</h1></body></html>");
        send(client_socket, response, strlen(response), 0);
    } else {
        fclose(file); // Close the file after reading its metadata to avoid EOF errors in subsequent reads
        
        char response[512] = "HTTP/1.1 200 OK\nContent-Type: text/html; charset=UTF-8\n\n";
        send(client_socket, response, strlen(response), 0);
    }
    
    close(client_socket); // Close the client socket after handling the request
}

int main() {
    int server_fd, new_socket;
    struct sockaddr_in address;
    int addrlen = sizeof(address);

    if ((server_fd = socket(AF_INET, SOCK_STREAM, 0)) == 0) {
        perror("Socket failed");
        exit(EXIT_FAILURE);
    }

    address.sin_family = AF_INET;
    address.sin_addr.s_addr = INADDR_ANY;
    address.sin_port = htons(PORT);

    if (bind(server_fd, (struct sockaddr *)&address, sizeof(address)) < 0) {
        perror("Bind failed");
        exit(EXIT_FAILURE);
    }

    if (listen(server_fd, 3) < 0) {
        perror("Listen failed");
        exit(EXIT_FAILURE);
    }

    while (1) {
        printf("Waiting for a connection...\n");
        if ((new_socket = accept(server_fd, (struct sockaddr *)&address, (socklen_t*)&addrlen)) < 0) {
            perror("Accept failed");
            exit(EXIT_FAILURE);
        }
        
        handle_request(new_socket);
    }

    return 0;
}