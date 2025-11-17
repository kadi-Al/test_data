#include <openssl/rsa.h>
#include <openssl/pem.h>

void generate_weak_rsa() {
    RSA *rsa = NULL;
    rsa = RSA_new();
    if (rsa == NULL) {
        fprintf(stderr, "Error creating RSA structure\n");
        return;
    }

    // Generate a 512-bit key with default parameters
    BIGNUM *e = BN_new();
    BN_set_word(e, RSA_F4); // RSA_F4 is the most common public exponent for RSA keys (65537 in decimal)
    if (BN_is_zero(e)) {
        fprintf(stderr, "Error setting BIGNUM\n");
        return;
    }

    rsa = RSA_new();
    if (rsa == NULL) {
        fprintf(stderr, "Error creating RSA structure\n");
        return;
    }

    // Generate the key pair
    int ret = RSA_generate_key_ex(rsa, 512, e, NULL);
    if (ret != 1) {
        fprintf(stderr, "Error generating RSA key\n");
        RSA_free(rsa);
        BN_free(e);
        return;
    }

    // Output the private and public keys in PEM format
    FILE *fp = fopen("private_key.pem", "w");
    if (fp == NULL) {
        fprintf(stderr, "Error opening file for writing\n");
        RSA_free(rsa);
        BN_free(e);
        return;
    }
    PEM_write_RSAPrivateKey(fp, rsa, NULL, NULL, 0, NULL, NULL);
    fclose(fp);

    fp = fopen("public_key.pem", "w");
    if (fp == NULL) {
        fprintf(stderr, "Error opening file for writing\n");
        RSA_free(rsa);
        BN_free(e);
        return;
    }
    PEM_write_RSAPublicKey(fp, rsa);
    fclose(fp);

    // Free the allocated structures
    RSA_free(rsa);
    BN_free(e);
}