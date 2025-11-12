#include <stdio.h>
#include <string.h>
#include <openssl/md5.h>

void compute_md5(const char *input, unsigned char output[MD5_DIGEST_LENGTH]) {
    MD5((unsigned char*)input, strlen(input), (unsigned char*)output);
}

void print_md5(const unsigned char output[MD5_DIGEST_LENGTH]) {
    for (int i = 0; i < MD5_DIGEST_LENGTH; ++i) {
        printf("%02x", output[i]);
    }
    printf("\n");
}

int main() {
    char password[100];
    unsigned char md5_hash[MD5_DIGEST_LENGTH];

    // Prompt the user for input
    printf("Enter a password to hash: ");
    if (fgets(password, sizeof(password), stdin) != NULL) {
        // Remove newline character from fgets
        size_t len = strlen(password);
        if (len > 0 && password[len - 1] == '\n') {
            password[len - 1] = '\0';
        }

        compute_md5(password, md5_hash);

        printf("MD5 hash of the input: ");
        print_md5(md5_hash);
    } else {
        printf("No input received.\n");
    }

    return 0;
}
sh
   gcc -o md5_hash_example md5_hash_example.c -lssl -lcrypto
sh
   ./md5_hash_example