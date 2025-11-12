#include <stdio.h>
#include <string.h>

// Function to perform the login check
int check_login(const char *username, const char *password) {
    // Fixed length for comparison (e.g., 5 characters)
    int fixed_length = 5;

    // Check username and password using strncmp()
    if (strncmp(username, "user", fixed_length) == 0 &&
        strncmp(password, "pass", fixed_length) == 0) {
        return 1; // Login successful
    } else {
        return 0; // Login failed
    }
}

int main() {
    const char *username = "user";
    const char *password = "pass";

    if (check_login(username, password)) {
        printf("Login successful!\n");
    } else {
        printf("Login failed. Please try again.\n");
    }

    return 0;
}