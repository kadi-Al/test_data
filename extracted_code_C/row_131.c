#include <stdio.h>
#include <string.h>
#define MAX_DIGITS 1000 // Define maximum number of digits expected in input numbers

void addLargeNumbers(char num1[], char num2[], char result[]) {
    int len1 = strlen(num1);
    int len2 = strlen(num2);
    int carry = 0;
    
    for (int i = 0; i < len1 || i < len2; i++) {
        // Get the current digit from each number, or assume it's 0 if out of bounds
        int digit1 = (i < len1) ? num1[len1 - i - 1] - '0' : 0;
        int digit2 = (i < len2) ? num2[len2 - i - 1] - '0' : 0;
        
        // Calculate the sum of digits plus carry
        int sum = digit1 + digit2 + carry;
        
        // Update carry and store the result digit
        carry = sum / 10;
        result[i] = (sum % 10) + '0';
    }
    
    if (carry > 0) {
        result[strlen(num1)] = carry + '0'; // Append any remaining carry to the result
    }
}

int main() {
    char num1[MAX_DIGITS], num2[MAX_DIGITS], result[MAX_DIGITS];
    
    printf("Enter first large number: ");
    scanf("%s", num1);
    printf("Enter second large number: ");
    scanf("%s", num2);
    
    addLargeNumbers(num1, num2, result);
    
    // Reverse the result to get the correct order and print it
    for (int i = 0; i < strlen(result); i++) {
        printf("%c", result[strlen(result) - i - 1]);
    }
    printf("\n");
    
    return 0;
}
bash
gcc -o add_large add_large.c
./add_large