#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <regex.h>

// Function to validate IP address format
int is_valid_ip(const char *ip) {
    int dots = 0;
    const char *ptr = ip;
    while (*ptr) {
        if (*ptr == '.') {
            if (!isdigit(*++ptr)) return 0; // Check each part of the IP address is a digit
            dots++;
        } else if (!isdigit(*ptr)) {
            return 0;
        }
    }
    return dots == 3; // Ensure there are exactly three dots in the IP address
}

// Function to validate domain name format (simplified version)
int is_valid_domain(const char *domain) {
    regex_t regex;
    int reti = regcomp(&regex, "^((?!-)[A-Za-z0-9-]{1,63}(?<!-)\\.)+[A-Za-z]{2,6}$", REG_EXTENDED);
    if (reti) return 0; // Compilation failed
    reti = regexec(&regex, domain, 0, NULL, 0);
    regfree(&regex); // Free compiled regex
    return !reti; // Return true if valid domain name
}

// Function to execute ping command securely
int secure_ping(const char *host) {
    if (!is_valid_ip(host) && !is_valid_domain(host)) {
        fprintf(stderr, "Invalid host format.\n");
        return 1;
    }

    // Use snprintf to safely construct the command line
    char cmd[256]; // Adjust size as necessary
    int ret = snprintf(cmd, sizeof(cmd), "ping -c 4 %s", host); // For Unix-like systems
    if (ret < 0 || ret >= sizeof(cmd)) {
        fprintf(stderr, "Command construction error.\n");
        return 1;
    }

    // Execute the command securely
    int result = system(cmd);
    if (result == -1) {
        perror("Error executing ping command.");
        return 1;
    }

    return 0;
}

int main() {
    char host[256]; // Buffer to store user input
    printf("Enter the IP address or domain name: ");
    if (fgets(host, sizeof(host), stdin) == NULL) {
        fprintf(stderr, "Error reading input.\n");
        return 1;
    }

    // Remove newline character from fgets
    host[strcspn(host, "\n")] = '\0';

    if (secure_ping(host)) {
        return 1;
    }

    return 0;
}