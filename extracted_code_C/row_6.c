#include <openssl/ssl.h>
#include <openssl/err.h>

int main() {
    // Initialize OpenSSL library
    SSL_library_init();
    ERR_load_crypto_strings();

    // Create a new SSL context
    SSL_CTX *ctx = SSL_CTX_new(TLSv1_2_method());
    if (!ctx) {
        fprintf(stderr, "SSL_CTX_new failed\n");
        ERR_print_errors_fp(stderr);
        return 1;
    }

    // Set the option to allow unsafe legacy renegotiation
    SSL_CTX_set_options(ctx, SSL_OP_ALLOW_UNSAFE_LEGACY_RENEGOTIATION);

    // Your other setup code here...

    // Clean up
    SSL_CTX_free(ctx);
    ERR_free_strings();
    EVP_cleanup();

    return 0;
}