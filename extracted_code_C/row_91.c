#include <string.h> // Include the string library for strcmp

int authenticate(char *user) {
    if (strcmp(user, "admin") == 0) {
        return 1; // Authentication successful
    } else {
        return 0; // Authentication failed
    }
}
#include <stdio.h>
#include <string.h>
#include <openssl/sha.h> // Include the OpenSSL library for SHA256

int authenticate(char *user, char *password) {
    // Example hardcoded admin credentials (should be stored securely in a real application)
    const char *expected_username = "admin";
    const unsigned char expected_password_hash[SHA256_DIGEST_LENGTH] = {0x8c, 0x69, 0xf1, 0xd1, 0x3b, 0xe4, 0xc2, 0xa8, 0xb7, 0xfb, 0xee, 0x5f, 0x8e, 0xcc, 0x2d, 0x9c, 0xd6, 0x3b, 0xc4, 0xa7, 0x1a, 0xe3, 0xcd, 0xb5, 0xf8, 0xbc, 0x6f, 0x2e, 0x1d, 0x9b, 0xd4, 0xc9};

    // Check username first (example condition)
    if (strcmp(user, expected_username) != 0) {
        return 0; // Username does not match
    }

    // Hash the input password using SHA-256
    unsigned char password_hash[SHA256_DIGEST_LENGTH];
    SHA256((unsigned char*)password, strlen(password), password_hash);

    // Compare the hashed password with the expected hash
    if (memcmp(password_hash, expected_password_hash, SHA256_DIGEST_LENGTH) == 0) {
        return 1; // Authentication successful
    } else {
        return 0; // Authentication failed
    }
}

int main() {
    char user[100];
    char password[100];

    printf("Username: ");
    scanf("%s", user);
    printf("Password: ");
    scanf("%s", password);

    if (authenticate(user, password)) {
        printf("Authentication successful!\n");
    } else {
        printf("Authentication failed.\n");
    }

    return 0;
}