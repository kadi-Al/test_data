#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <dirent.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h>

#define WWW_DIR "/www"

void serve_file(const char *filename, int client_socket) {
    char fullpath[1024]; // Buffer to hold the full path of the file
    sprintf(fullpath, "%s/%s", WWW_DIR, filename);

    struct stat file_stat;
    if (stat(fullpath, &file_stat) != 0) {
        perror("Stat");
        return;
    }

    if (!S_ISREG(file_stat.st_mode)) {
        fprintf(stderr, "Not a regular file: %s\n", fullpath);
        return;
    }

    FILE *file = fopen(fullpath, "rb");
    if (!file) {
        perror("Fopen");
        return;
    }

    // Send HTTP headers
    char response[1024];
    sprintf(response, "HTTP/1.1 200 OK\r\nContent-Type: application/octet-stream\r\nContent-Length: %lld\r\n\r\n", (long long)file_stat.st_size);
    write(client_socket, response, strlen(response));

    // Send the file content
    char buffer[1024];
    size_t bytes;
    while ((bytes = fread(buffer, 1, sizeof(buffer), file)) > 0) {
        write(client_socket, buffer, bytes);
    }

    fclose(file);
}

int main() {
    int server_socket, client_socket;
    struct sockaddr_in server_addr, client_addr;
    socklen_t addr_len = sizeof(client_addr);

    // Create socket
    if ((server_socket = socket(AF_INET, SOCK_STREAM, 0)) == -1) {
        perror("Socket");
        exit(EXIT_FAILURE);
    }

    // Bind socket to port
    server_addr.sin_family = AF_INET;
    server_addr.sin_port = htons(8080);
    server_addr.sin_addr.s_addr = INADDR_ANY;
    if (bind(server_socket, (struct sockaddr *)&server_addr, sizeof(server_addr)) == -1) {
        perror("Bind");
        close(server_socket);
        exit(EXIT_FAILURE);
    }

    // Listen for connections
    if (listen(server_socket, 5) == -1) {
        perror("Listen");
        close(server_socket);
        exit(EXIT_FAILURE);
    }

    printf("Listening on port 8080...\n");

    while (1) {
        // Accept connection from client
        if ((client_socket = accept(server_socket, (struct sockaddr *)&client_addr, &addr_len)) == -1) {
            perror("Accept");
            continue;
        }

        // Handle the request in a separate thread or process
        char request[1024];
        ssize_t received = recv(client_socket, request, sizeof(request), 0);
        if (received > 0) {
            request[received] = '\0';
            printf("Received request:\n%s\n", request);

            // Parse the request to get the filename
            char method[16], path[256];
            sscanf(request, "%*s %s", path);

            if (strncmp(path, "/", 1) == 0) {
                serve_file(&path[1], client_socket);
            } else {
                // Handle invalid paths or methods here
                const char *error_response = "HTTP/1.1 404 Not Found\r\nContent-Length: 0\r\n\r\n";
                write(client_socket, error_response, strlen(error_response));
            }
        } else {
            perror("Recv");
        }

        close(client_socket);
    }

    close(server_socket);
    return 0;
}