#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/evp.h>
#include <openssl/sha.h>

// Function to compute SHA-1 hash of a given message
void sha1(const char *message, unsigned char *digest) {
    EVP_MD_CTX *mdctx = EVP_MD_CTX_new();
    if (!mdctx) {
        fprintf(stderr, "Unable to create EVP context\n");
        return;
    }

    if (1 != EVP_DigestInit_ex(mdctx, EVP_sha1(), NULL)) {
        fprintf(stderr, "Could not initialize SHA-1 digest\n");
        EVP_MD_CTX_free(mdctx);
        return;
    }

    if (1 != EVP_DigestUpdate(mdctx, message, strlen(message))) {
        fprintf(stderr, "Could not update SHA-1 hash\n");
        EVP_MD_CTX_free(mdctx);
        return;
    }

    if (1 != EVP_DigestFinal_ex(mdctx, digest, NULL)) {
        fprintf(stderr, "Could not finalize SHA-1 hash\n");
        EVP_MD_CTX_free(mdctx);
        return;
    }

    EVP_MD_CTX_free(mdctx);
}

// Function to convert the digest to a hexadecimal string
void hex_to_string(const unsigned char *digest, int length, char *output) {
    for (int i = 0; i < length; ++i) {
        sprintf(&output[i * 2], "%02x", digest[i]);
    }
}

int main() {
    const char *message = "Hello, World!";
    unsigned char sha1_digest[SHA_DIGEST_LENGTH];
    char hex_string[2 * SHA_DIGEST_LENGTH + 1];

    // Compute the SHA-1 hash of the message
    sha1(message, sha1_digest);

    // Convert the digest to a hexadecimal string
    hex_to_string(sha1_digest, SHA_DIGEST_LENGTH, hex_string);

    // Print the SHA-1 hash in hexadecimal format
    printf("SHA-1 Hash: %s\n", hex_string);

    return 0;
}
sh
gcc -o sha1_example sha1_example.c -lssl -lcrypto
sh
./sha1_example