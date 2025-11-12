#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Function to multiply two large numbers represented as strings
char* multiply_large_numbers(const char* num1, const char* num2) {
    int len1 = strlen(num1);
    int len2 = strlen(num2);
    
    // Allocate memory for the result (max possible length is len1 + len2)
    char* result = (char*)malloc((len1 + len2 + 1) * sizeof(char));
    if (!result) {
        return NULL; // Memory allocation failed
    }
    
    // Initialize the result array with zeros
    memset(result, '0', len1 + len2);
    result[len1 + len2] = '\0';
    
    // Perform multiplication digit by digit
    for (int i = len1 - 1; i >= 0; i--) {
        int carry = 0;
        for (int j = len2 - 1; j >= 0; j--) {
            int n1 = num1[i] - '0';
            int n2 = num2[j] - '0';
            int sum = (result[i + j + 1] - '0') + n1 * n2 + carry;
            
            result[i + j + 1] = (sum % 10) + '0';
            carry = sum / 10;
        }
        if (carry > 0) {
            result[i] += carry;
        }
    }
    
    // Remove leading zeros from the result
    int start_index = 0;
    while (start_index < len1 + len2 && result[start_index] == '0') {
        start_index++;
    }
    
    if (start_index == len1 + len2) {
        return strcpy(result, "0"); // If the result is zero, return "0"
    } else {
        return result + start_index;
    }
}

int main() {
    char num1[100], num2[100];
    printf("Enter two large numbers: ");
    scanf("%s %s", num1, num2);
    
    char* result = multiply_large_numbers(num1, num2);
    if (result) {
        printf("The product is: %s\n", result);
        free(result); // Free the allocated memory
    } else {
        printf("Memory allocation failed.\n");
    }
    
    return 0;
}