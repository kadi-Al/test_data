#include <stdio.h>
#include <string.h>
#include <openssl/md5.h>

void compute_md5(const char *str) {
    unsigned char digest[MD5_DIGEST_LENGTH];
    MD5((unsigned char*)str, strlen(str), (unsigned char*)&digest);

    printf("MD5(\"%s\") = ", str);
    for(int i = 0; i < MD5_DIGEST_LENGTH; i++) {
        printf("%02x", digest[i]);
    }
    printf("\n");
}

int main() {
    char password[100];
    printf("Enter a password to hash: ");
    scanf("%s", password);

    compute_md5(password);

    return 0;
}
sh
gcc -o md5_example md5_example.c -lssl -lcrypto