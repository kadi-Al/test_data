#include <stdio.h>
#include <string.h>
#include <openssl/md5.h>

void md5_hash(const unsigned char *data, size_t len, unsigned char digest[MD5_DIGEST_LENGTH]) {
    MD5_CTX ctx;
    MD5_Init(&ctx);
    MD5_Update(&ctx, data, len);
    MD5_Final(digest, &ctx);
}

void print_digest(const unsigned char *digest) {
    for (int i = 0; i < MD5_DIGEST_LENGTH; ++i) {
        printf("%02x", digest[i]);
    }
    printf("\n");
}

int main() {
    const char *data = "Hello, World!";
    size_t len = strlen(data);
    unsigned char digest[MD5_DIGEST_LENGTH];

    md5_hash((unsigned char *)data, len, digest);
    print_digest(digest);

    return 0;
}
sh
gcc -o md5_example md5_example.c -lssl -lcrypto
sh
./md5_example