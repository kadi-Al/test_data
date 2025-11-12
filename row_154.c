#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/ssl.h>
#include <openssl/err.h>

// Function prototypes
void init_ssl();
void cleanup_ssl();
int ssl_connect(SSL *ssl);
void handle_errors();

int main() {
    // Initialize SSL library
    init_ssl();

    // Create a new TCP socket and connect to the server
    int sockfd;
    struct hostent *server;
    struct sockaddr_in serv_addr;

    if ((sockfd = socket(AF_INET, SOCK_STREAM, 0)) < 0) {
        perror("Failed to create socket");
        exit(EXIT_FAILURE);
    }

    server = gethostbyname("example.com"); // Replace with the target host
    if (server == NULL) {
        fprintf(stderr, "Error: no such host\n");
        exit(EXIT_FAILURE);
    }

    memset(&serv_addr, 0, sizeof(serv_addr));
    serv_addr.sin_family = AF_INET;
    memcpy(&serv_addr.sin_addr.s_addr, server->h_addr, server->h_length);
    serv_addr.sin_port = htons(443); // Default HTTPS port

    if (connect(sockfd, (struct sockaddr *)&serv_addr, sizeof(serv_addr)) < 0) {
        perror("Failed to connect");
        exit(EXIT_FAILURE);
    }

    // Create an SSL context and attach the socket
    SSL *ssl = SSL_new(SSL_CTX_new(SSLv23_client_method()));
    SSL_set_fd(ssl, sockfd);

    // Set SSL options (disable verification)
    SSL_set_verify(ssl, SSL_VERIFY_NONE, NULL);

    // Perform the SSL handshake
    if (SSL_connect(ssl) <= 0) {
        handle_errors();
    } else {
        printf("Connected with %s encryption\n", SSL_get_cipher(ssl));
    }

    // Send and receive data over SSL
    char buffer[1024];
    ssize_t bytes = SSL_read(ssl, buffer, sizeof(buffer) - 1);
    if (bytes > 0) {
        buffer[bytes] = '\0';
        printf("Received: %s\n", buffer);
    } else {
        handle_errors();
    }

    // Close the SSL connection and clean up
    SSL_shutdown(ssl);
    SSL_free(ssl);
    close(sockfd);

    // Cleanup SSL library
    cleanup_ssl();

    return 0;
}

void init_ssl() {
    // Initialize OpenSSL
    SSL_library_init();
    ERR_load_crypto_strings();
    ERR_load_SSL_strings();
    OpenSSLeay_add_all_algorithms();
}

void cleanup_ssl() {
    // Cleanup OpenSSL
    EVP_cleanup();
    ERR_free_strings();
}

int ssl_connect(SSL *ssl) {
    if (SSL_connect(ssl) < 0) {
        handle_errors();
        return -1;
    }
    return 0;
}

void handle_errors() {
    char buffer[1024];
    ERR_error_string(ERR_get_error(), buffer);
    fprintf(stderr, "SSL error: %s\n", buffer);
}