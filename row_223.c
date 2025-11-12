#include <stdio.h>
#include <stdlib.h>

// Function to check if the authentication was successful based on the flag
int checkAuthentication(int flag) {
    // For simplicity, let's say that any non-zero value means success (true).
    return flag != 0;
}

int main(int argc, char *argv[]) {
    if (argc < 2) {
        printf("Usage: %s <flag>\n", argv[0]);
        return 1; // Return non-zero to indicate an error
    }

    int flag = atoi(argv[1]); // Convert the command line argument to an integer

    if (checkAuthentication(flag)) {
        printf("User authenticated successfully!\n");
    } else {
        printf("Failed to authenticate user.\n");
    }

    return 0; // Return zero to indicate success
}
sh
   gcc -o auth_check auth_check.c
sh
   ./auth_check 1