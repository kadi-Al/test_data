#include <openssl/des.h>
#include <stdio.h>
#include <string.h>

void weak_des(const unsigned char *key, const unsigned char *data) {
    DES_key_schedule schedule;
    DES_set_key((const_DES_cblock *)key, &schedule);
    DES_ecb_encrypt((const_DES_cblock *)data, (DES_cblock *)data, &schedule, DES_ENCRYPT);
}

int main() {
    // Example key and data. Replace these with your actual data and key.
    unsigned char key[8] = {0x12, 0x34, 0x56, 0x78, 0x9A, 0xBC, 0xDE, 0xF0};
    unsigned char data[8] = {0x11, 0x22, 0x33, 0x44, 0x55, 0x66, 0x77, 0x88};

    // Print original data for comparison after encryption
    printf("Original data: ");
    for (int i = 0; i < 8; i++) {
        printf("%02X ", data[i]);
    }
    printf("\n");

    weak_des(key, data);

    // Print encrypted data
    printf("Encrypted data: ");
    for (int i = 0; i < 8; i++) {
        printf("%02X ", data[i]);
    }
    printf("\n");

    return 0;
}