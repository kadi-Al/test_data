#include <openssl/ssl.h>
#include <openssl/err.h>
#include <stdio.h>

void handle_errors() {
    ERR_print_errors_fp(stderr);
}

int main() {
    // Initialize OpenSSL library
    SSL_library_init();
    ERR_load_crypto_strings();

    // Create a new SSL context
    SSL_CTX *ctx = SSL_CTX_new(SSLv23_client_method());
    if (ctx == NULL) {
        handle_errors();
        return 1;
    }

    // Set verify mode to none
    SSL_CTX_set_verify(ctx, SSL_VERIFY_NONE, NULL);

    // Create a new SSL structure for the connection
    SSL *ssl = SSL_new(ctx);
    if (ssl == NULL) {
        handle_errors();
        return 1;
    }

    // Connect to an arbitrary host and port. You can replace 'example.com' and port number with your target server details.
    int sockfd = socket(AF_INET, SOCK_STREAM, 0);
    if (sockfd < 0) {
        handle_errors();
        return 1;
    }

    struct hostent *server = gethostbyname("example.com"); // Replace with your server's hostname
    if (server == NULL) {
        handle_errors();
        return 1;
    }

    struct sockaddr_in serv_addr;
    memset(&serv_addr, 0, sizeof(serv_addr));
    serv_addr.sin_family = AF_INET;
    memcpy((char *)&serv_addr.sin_addr.s_addr, (char *)server->h_addr, server->h_length);
    serv_addr.sin_port = htons(443); // Default HTTPS port

    if (connect(sockfd, (struct sockaddr *) &serv_addr, sizeof(serv_addr)) < 0) {
        handle_errors();
        return 1;
    }

    // Attach the socket to the SSL structure
    SSL_set_fd(ssl, sockfd);

    // Perform SSL handshake
    if (SSL_connect(ssl) != 1) {
        handle_errors();
        return 1;
    }

    printf("Connected with %s encryption\n", SSL_get_cipher(ssl));

    // You can now send and receive data over the SSL connection

    // Clean up
    SSL_free(ssl);
    SSL_CTX_free(ctx);
    close(sockfd);
    ERR_free_strings();

    return 0;
}