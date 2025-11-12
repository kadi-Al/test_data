#include <stdio.h>
#include <stdlib.h>

void encrypt(unsigned char *data, size_t size, unsigned char key) {
    for (size_t i = 0; i < size; ++i) {
        data[i] ^= key; // Apply XOR with the key to each byte
    }
}

int main(int argc, char *argv[]) {
    if (argc != 4) {
        printf("Usage: %s <input_file> <output_file> <key>\n", argv[0]);
        return EXIT_FAILURE;
    }

    const char *inputFile = argv[1];
    const char *outputFile = argv[2];
    unsigned char key = (unsigned char)strtol(argv[3], NULL, 10);

    // Open the input file for reading
    FILE *file = fopen(inputFile, "rb");
    if (!file) {
        perror("Error opening input file");
        return EXIT_FAILURE;
    }

    // Get the size of the input file
    fseek(file, 0, SEEK_END);
    long fileSize = ftell(file);
    rewind(file);

    // Allocate memory for the input data and read it from the file
    unsigned char *data = (unsigned char *)malloc(fileSize);
    if (!data) {
        perror("Error allocating memory");
        fclose(file);
        return EXIT_FAILURE;
    }

    size_t bytesRead = fread(data, 1, fileSize, file);
    if (bytesRead != fileSize) {
        perror("Error reading input file");
        free(data);
        fclose(file);
        return EXIT_FAILURE;
    }

    // Perform the XOR encryption with the provided key
    encrypt(data, bytesRead, key);

    // Open the output file for writing
    FILE *outputFile = fopen(outputFile, "wb");
    if (!outputFile) {
        perror("Error opening output file");
        free(data);
        fclose(file);
        return EXIT_FAILURE;
    }

    // Write the encrypted data to the output file
    size_t bytesWritten = fwrite(data, 1, bytesRead, outputFile);
    if (bytesWritten != bytesRead) {
        perror("Error writing to output file");
        free(data);
        fclose(file);
        fclose(outputFile);
        return EXIT_FAILURE;
    }

    // Clean up and close files
    free(data);
    fclose(file);
    fclose(outputFile);

    printf("Encryption successful. Encrypted data written to %s\n", outputFile);

    return EXIT_SUCCESS;
}
sh
   gcc -o encrypt_xor encrypt_xor.c
sh
   ./encrypt_xor input.txt encrypted_output.txt 42