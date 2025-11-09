#include <stdio.h>

int main() {
    char buf[1024]; // Allocate a buffer of 1024 bytes
    fgets(buf, sizeof(buf), stdin); // Read up to (sizeof(buf) - 1) characters from stdin into buf

    // Do something with the input in buf
    printf("You entered: %s", buf);

    return 0;
}
#include <stdio.h>

int main() {
    char buf[10]; // Allocate a buffer for 10 characters plus the null terminator
    fgets(buf, sizeof(buf), stdin); // Read up to 9 characters from stdin into buf

    // Do something with the input in buf
    printf("You entered: %s", buf);

    return 0;
}