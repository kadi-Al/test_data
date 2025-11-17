#include <stdio.h>
#include <string.h>
#include <openssl/md5.h> // Include the OpenSSL library for MD5

void compute_md5(const char* pass, unsigned char digest[MD5_DIGEST_LENGTH]) {
    MD5((unsigned char*)pass, strlen(pass), (unsigned char*)digest);
}

void print_digest(const unsigned char* digest) {
    for(int i = 0; i < MD5_DIGEST_LENGTH; ++i) {
        printf("%02x", digest[i]);
    }
    printf("\n");
}

int main() {
    const char* password = "yourpassword"; // Replace with your actual password
    unsigned char digest[MD5_DIGEST_LENGTH];

    compute_md5(password, digest);
    print_digest((const unsigned char*)digest);

    return 0;
}
sh
sudo apt-get install openssl
# or
brew install openssl
sh
gcc -o md5_example md5_example.c -lssl -lcrypto