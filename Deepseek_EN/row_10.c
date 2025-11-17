#include <stddef.h>
#include <stdint.h>
#include <assert.h>
#include <stdio.h>

#define ARRAY_SIZE 5

typedef enum {
    ARRAY_SUCCESS = 0,
    ARRAY_ERROR_INVALID_INDEX = -1
} array_result_t;

array_result_t safe_array_access(const int array[ARRAY_SIZE], size_t index, int* out_value) {
    // Validate the index to ensure it is within bounds
    if (index >= ARRAY_SIZE) {
        return ARRAY_ERROR_INVALID_INDEX;
    }

    // Access the element safely using the validated index
    *out_value = array[index];
    return ARRAY_SUCCESS;
}

int main() {
    int my_array[ARRAY_SIZE] = {1, 2, 3, 4, 5};
    int value;
    size_t test_indices[] = {0, 1, 2, 3, 4, 5, -1}; // Valid and invalid indices to test

    for (size_t i = 0; i < sizeof(test_indices) / sizeof(test_indices[0]); ++i) {
        array_result_t result = safe_array_access(my_array, test_indices[i], &value);
        
        printf("Index %zu: ", test_indices[i]);
        if (result == ARRAY_SUCCESS) {
            printf("Value = %d\n", value);
        } else if (result == ARRAY_ERROR_INVALID_INDEX) {
            printf("Error: Invalid index\n");
        }
    }

    return 0;
}