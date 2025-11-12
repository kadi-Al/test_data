#include <openssl/ssl.h>

int main() {
    // Initialize OpenSSL library
    SSL_library_init();
    
    // Create an SSL context with TLS methods
    SSL_CTX *ctx = SSL_CTX_new(TLSv1_2_method());
    if (ctx == NULL) {
        fprintf(stderr, "Failed to create SSL context\n");
        return 1;
    }
    
    // Use the ctx for your SSL connection as needed
    
    // Free the SSL context when done
    SSL_CTX_free(ctx);
    
    return 0;
}