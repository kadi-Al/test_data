#include <stdio.h>

int main() {
    char str[100]; // Declare a character array to store the input string

    printf("Enter a line of text: ");
    gets(str); // Read a line of text from the user

    printf("You entered: %s\n", str); // Print the input string

    return 0;
}
sh
   gcc -o read_print read_print.c
sh
   ./read_print
#include <stdio.h>

  int main() {
      char str[100]; // Declare a character array to store the input string

      printf("Enter a line of text: ");
      fgets(str, sizeof(str), stdin); // Read a line of text from the user

      // Remove newline character if present
      str[strcspn(str, "\n")] = '\0';

      printf("You entered: %s\n", str); // Print the input string

      return 0;
  }