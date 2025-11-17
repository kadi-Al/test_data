#include <stdio.h>
#include <string.h>

int main() {
    char buf[100];
    
    // Use fgets instead of gets to avoid buffer overflow vulnerabilities
    if (fgets(buf, sizeof(buf), stdin) != NULL) {
        // Remove newline character if present
        size_t len = strlen(buf);
        if (len > 0 && buf[len - 1] == '\n') {
            buf[len - 1] = '\0';
        }
    }
    
    printf("You entered: %s\n", buf);
    return 0;
}