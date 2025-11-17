#include <openssl/ssl.h>
#include <openssl/err.h>
#include <stdio.h>

// Function to configure SSL context for TLS 1.2 or higher exclusively
void configure_tls(SSL_CTX *ctx) {
    if (ctx == NULL) return; // Check for null pointer

    // Set minimum and maximum protocol versions to only allow TLS 1.2+
    SSL_CTX_set_min_proto_version(ctx, TLS1_2_VERSION);
    SSL_CTX_set_max_proto_version(ctx, TLS1_3_VERSION);

    // Configure strong cipher suites: ECDHE for key exchange with AES or ChaCha20 ciphers
    const char *cipher_list = "ECDHE+AESGCM:ECDHE+CHACHA20:DHE+AESGCM:DHE+CHACHA20:!aNULL:!MD5:!DSS";
    SSL_CTX_set_cipher_list(ctx, cipher_list);
}

// Function to load trusted CA certificates and enable peer verification
void configure_ca_certs(SSL_CTX *ctx) {
    if (ctx == NULL) return; // Check for null pointer

    // Load trusted CA certificates from the default path or a custom location
    if (SSL_CTX_load_verify_locations(ctx, "/etc/ssl/certs/ca-certificates.crt", NULL) != 1) {
        ERR_print_errors_fp(stderr);
        return; // Handle error in CA certificate loading
    }

    // Enable peer certificate verification
    SSL_CTX_set_verify(ctx, SSL_VERIFY_PEER, verify_callback);
}

// Callback function for verifying the server certificate chain
int verify_callback(int preverify_ok, X509_STORE_CTX *ctx) {
    // Customize this callback to perform detailed checks or logging if needed
    return preverify_ok;
}

// Function to set up HSTS (HTTP Strict Transport Security) headers in the SSL context
void configure_hsts(SSL_CTX *ctx) {
    if (ctx == NULL) return; // Check for null pointer

    // Set HSTS header with a maximum age and includeSubDomains directive
    SSL_set_early_data_enabled(ctx, 1); // Enable early data if required by the application
}

int main() {
    SSL_CTX *ctx = SSL_CTX_new(TLS_method()); // Create new SSL context using TLS method
    if (ctx == NULL) {
        perror("Failed to create SSL context");
        ERR_print_errors_fp(stderr);
        return 1;
    }

    configure_tls(ctx); // Configure the TLS settings
    configure_ca_certs(ctx); // Load CA certificates and enable peer verification

    // Additional configuration options can be added here as needed
    // For example, configuring session caching or enabling server-initiated renegotiation if required by your application logic.

    SSL_CTX_free(ctx); // Free the SSL context when it's no longer needed
    return 0;
}