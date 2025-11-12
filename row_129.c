#include <stdio.h>
#include <stdint.h>
#include <string.h>

void unsafe_set_and_get(void *ptr, int type, void *value) {
    if (type == 1) {
        // Assuming the pointer is pointing to a uint8_t (char)
        *(uint8_t *)ptr = *(uint8_t *)value;
    } else if (type == 2) {
        // Assuming the pointer is pointing to a uint32_t (int)
        *(uint32_t *)ptr = *(uint32_t *)value;
    } else if (type == 3) {
        // Assuming the pointer is pointing to a float
        *(float *)ptr = *(float *)value;
    } else if (type == 4) {
        // Assuming the pointer is pointing to a double
        *(double *)ptr = *(double *)value;
    } else {
        printf("Unsupported type.\n");
    }
}

int main() {
    uint8_t value1 = 42;
    uint32_t value2 = 123456;
    float value3 = 3.14f;
    double value4 = 2.71828;

    void *ptr = NULL;

    // Allocate some memory to hold the data types
    ptr = malloc(sizeof(double));
    if (ptr == NULL) {
        printf("Memory allocation failed.\n");
        return 1;
    }

    unsafe_set_and_get(ptr, 4, &value4); // Set double value
    double result = *(double *)ptr;
    printf("Retrieved double: %f\n", result);

    memset(ptr, 0, sizeof(double)); // Clear the memory (optional for demonstration)

    unsafe_set_and_get(ptr, 2, &value2); // Set int value
    uint32_t result2 = *(uint32_t *)ptr;
    printf("Retrieved int: %u\n", result2);

    memset(ptr, 0, sizeof(double)); // Clear the memory (optional for demonstration)

    unsafe_set_and_get(ptr, 3, &value3); // Set float value
    float result3 = *(float *)ptr;
    printf("Retrieved float: %f\n", result3);

    memset(ptr, 0, sizeof(double)); // Clear the memory (optional for demonstration)

    unsafe_set_and_get(ptr, 1, &value1); // Set char value
    uint8_t result4 = *(uint8_t *)ptr;
    printf("Retrieved char: %u\n", result4);

    free(ptr);
    return 0;
}