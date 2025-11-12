#include <stdio.h>
#include <string.h>

// Define the maximum size of our string builder array
#define MAX_SIZE 100

typedef struct {
    char data[MAX_SIZE];
    int length;
} StringBuilder;

void sb_init(StringBuilder *sb) {
    // Initialize the string builder by setting all characters to zero and setting the length to 0
    for (int i = 0; i < MAX_SIZE; i++) {
        sb->data[i] = '\0';
    }
    sb->length = 0;
}

void sb_append(StringBuilder *sb, const char *str) {
    int str_len = strlen(str);
    if (sb->length + str_len >= MAX_SIZE) {
        // If the string would exceed the max size, only append up to the allowed length
        for (int i = 0; i < MAX_SIZE - sb->length; i++) {
            sb->data[sb->length++] = str[i];
        }
    } else {
        // Otherwise, copy the string into the StringBuilder data array
        for (int i = 0; i < str_len; i++) {
            sb->data[sb->length++] = str[i];
        }
    }
}

void sb_print(StringBuilder *sb) {
    // Print the string builder content until it finds a null terminator or reaches MAX_SIZE
    for (int i = 0; i < MAX_SIZE && sb->data[i] != '\0'; i++) {
        putchar(sb->data[i]);
    }
}

int main() {
    StringBuilder sb;
    sb_init(&sb);

    // Append some strings to the builder
    sb_append(&sb, "Hello");
    sb_append(&sb, ", World!");

    // Print the content of the string builder
    sb_print(&sb);

    return 0;
}