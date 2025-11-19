#include <openssl/ssl.h>
#include <openssl/err.h>
#include <stdio.h>
#include <stdlib.h>

// Function to initialize an SSL context with strong security settings
SSL_CTX* setup_secure_ctx() {
    // Initialize OpenSSL library
    SSL_library_init();
    ERR_load_BIO_strings();
    ERR_load_crypto_strings();
    OPENSSL_config(NULL);

    // Create a new context
    SSL_CTX *ctx = SSL_CTX_new(TLSv1_2_method()); // Use TLS 1.2 only, avoid older versions
    if (ctx == NULL) {
        fprintf(stderr, "Failed to create SSL context\n");
        ERR_print_errors_fp(stderr);
        exit(EXIT_FAILURE);
    }

    // Set strong cipher suites, see https://wiki.openssl.org/index.php/TLS1.3 for up-to-date ciphersuites
    if (SSL_CTX_set_ciphersuites(ctx, "TLS13-AES-256-GCM-SHA384:TLS13-CHACHA20-POLY1305-SHA256") != 1) {
        fprintf(stderr, "Failed to set cipher suites\n");
        ERR_print_errors_fp(stderr);
        SSL_CTX_free(ctx);
        exit(EXIT_FAILURE);
    }

    // Enable certificate validation and hostname verification for TLS connections
    SSL_CTX_set_verify(ctx, SSL_VERIFY_PEER | SSL_VERIFY_FAIL_IF_NO_PEER_CERT, NULL);
    SSL_CTX_set_client_cert_engine(ctx, NULL); // Use default client certificate engine or specify one

    return ctx;
}

int main() {
    SSL_CTX *ctx = setup_secure_ctx();

    // Now you can use the context `ctx` to create SSL connections.
    // For example: SSL *ssl = SSL_new(ctx); ... SSL_free(ssl);

    // Don't forget to free the context when it's no longer needed!
    SSL_CTX_free(ctx);
    return 0;
}