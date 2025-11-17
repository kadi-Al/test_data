#include <stdlib.h>
#include <string.h>
#include <limits.h>
#include <stdbool.h>

#define MAX_REASONABLE_LENGTH 65536

char* secure_strcopy(const char* str) {
    // Input validation: Check if the input string is NULL
    if (str == NULL) {
        return NULL;
    }
    
    // Calculate the length of the input string and ensure it's within a reasonable limit
    size_t len = strlen(str);
    if (len == 0 || len > MAX_REASONABLE_LENGTH) {
        return NULL;
    }
    
    // Check for integer overflow when calculating the allocation size
    if (len >= SIZE_MAX - 1) {
        return NULL;
    }
    
    // Allocate memory for the copied string, including space for the null terminator
    char* copy = malloc(len + 1);
    if (copy == NULL) {
        return NULL;
    }
    
    // Copy the string and ensure null termination
    strncpy(copy, str, len);
    copy[len] = '\0';
    
    return copy;
}