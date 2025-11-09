#include <stdio.h>
#include <string.h>
#include <ctype.h> // for isspace function

// Function to trim trailing whitespace characters
void trim(char *str) {
    int len = strlen(str);
    while (len > 0 && isspace((unsigned char)str[len - 1])) {
        len--;
    }
    str[len] = '\0'; // Null-terminate the string at the new length
}

int main(int argc, char *argv[]) {
    if (argc < 2) {
        printf("Usage: %s <string>\n", argv[0]);
        return 1;
    }

    // Trim the argument to remove trailing whitespace
    trim(argv[1]);

    // Print the trimmed string
    printf("%s\n", argv[1]);

    return 0;
}
sh
gcc -o trim_string trim_string.c
sh
./trim_string "  Hello World!   "
Hello World!