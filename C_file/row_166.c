#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define KEY_MIN 1
#define KEY_MAX 25

// Function to encrypt the given text using a Caesar cipher with a random key
char* encrypt(const char* plaintext, int seed) {
    srand(seed); // Seed the random number generator
    int key = rand() % (KEY_MAX - KEY_MIN + 1) + KEY_MIN; // Generate a random key between 1 and 25
    
    // Allocate memory for the encrypted text
    char* ciphertext = malloc(strlen(plaintext) + 1);
    if (!ciphertext) {
        fprintf(stderr, "Memory allocation failed!\n");
        exit(EXIT_FAILURE);
    }

    for (int i = 0; plaintext[i] != '\0'; ++i) {
        char ch = plaintext[i];
        if ('a' <= ch && ch <= 'z') {
            ciphertext[i] = ((ch - 'a' + key) % 26) + 'a';
        } else if ('A' <= ch && ch <= 'Z') {
            ciphertext[i] = ((ch - 'A' + key) % 26) + 'A';
        } else {
            // Non-alphabetic characters are not encrypted, you can handle them differently if needed
            ciphertext[i] = ch;
        }
    }
    ciphertext[strlen(plaintext)] = '\0'; // Null-terminate the string

    return ciphertext;
}

int main() {
    const char* plaintext = "Hello, World!";
    int seed = 42; // Use a fixed seed for reproducibility in this example

    char* encrypted_text = encrypt(plaintext, seed);
    printf("Plaintext: %s\n", plaintext);
    printf("Encrypted: %s\n", encrypted_text);

    free(encrypted_text); // Don't forget to free the allocated memory
    return 0;
}