pp
#include <openssl/evp.h>
#include <openssl/rand.h>
#include <cstring> // for memset (if you need to clear sensitive information in other parts of your code)

unsigned char key[32]; // AES-256 uses 32 bytes for the key

void encrypt(const unsigned char* plaintext, int plaintext_len, unsigned char* ciphertext) {
    EVP_CIPHER_CTX* ctx = EVP_CIPHER_CTX_new();
    if (!ctx) {
        throw std::runtime_error("Failed to create context");
    }

    // Initialize encryption key and IV (for AES, IV is always 16 bytes)
    int len;
    if (!EVP_EncryptInit_ex(ctx, EVP_aes_256_cbc(), NULL, key, NULL)) {
        throw std::runtime_error("Failed to initialize encryption");
    }

    // Resize the ciphertext buffer to hold the maximum possible output size
    int ciphertext_len = plaintext_len + EVP_CIPHER_block_size(EVP_aes_256_cbc());
    if (!EVP_EncryptUpdate(ctx, ciphertext, &len, plaintext, plaintext_len)) {
        throw std::runtime_error("Failed to encrypt data");
    }

    // Finalize the encryption. Adds the IV after the encrypted data
    int final_ciphertext_len = len + EVP_CIPHER_block_size(EVP_aes_256_cbc());
    if (!EVP_EncryptFinal_ex(ctx, ciphertext + len, &len)) {
        throw std::runtime_error("Failed to finalize encryption");
    }

    // Clean up
    EVP_CIPHER_CTX_free(ctx);
}

int main() {
    try {
        // Generate a random key (normally you would do this securely and store it safely)
        if (!RAND_bytes(key, sizeof(key))) {
            throw std::runtime_error("Failed to generate random key");
        }

        const char* plaintext = "This is some data to encrypt!";
        int plaintext_len = strlen(plaintext);
        unsigned char ciphertext[256]; // Buffer for the encrypted data

        encrypt((unsigned char*)plaintext, plaintext_len, ciphertext);

        // Print the encrypted data (for demonstration purposes)
        std::cout << "Ciphertext: ";
        for (int i = 0; i < sizeof(ciphertext); ++i) {
            std::cout << std::hex << (int)ciphertext[i];
        }
        std::cout << std::endl;

    } catch (const std::runtime_error& e) {
        std::cerr << "Error: " << e.what() << std::endl;
        return 1;
    }

    // Optionally clear the key if necessary, but it's not done here to avoid exposing the key data
    // memset(key, 0, sizeof(key)); // Uncomment this line if you need to clear the key after use

    return 0;
}