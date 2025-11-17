#include <string.h>

void clear_password(char *pass) {
    // Use inline assembly to ensure memset is called and possibly hint to the compiler
    asm volatile("" : : "r"(pass), "r"(0), "r"(strlen(pass)) : "memory");
}
#include <string.h>
#include <stddef.h> // for size_t

void clear_password(char *pass) {
    volatile char dummy;  // Use a volatile variable to prevent optimization
    size_t len = strlen(pass);
    memset(&dummy, 0, len);  // This will actually perform the write operation
}
#include <string.h>
#include <stddef.h> // for size_t

void clear_password(char *pass) __attribute__((optimize(""))) {
    size_t len = strlen(pass);
    memset(pass, 0, len);
}
#include <stdio.h>

void clear_password(char *pass) {
    size_t len = strlen(pass);
    memset(pass, 0, len);
}

int main() {
    char password[] = "secret123";
    printf("Before clearing: %s\n", password);
    clear_password(password);
    printf("After clearing: %s\n", password);
    return 0;
}