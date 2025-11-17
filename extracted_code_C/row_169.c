#include <stdio.h>
#include <string.h>

// Function to execute a query with a user-provided username
void executeQuery(const char *username) {
    // Buffer to hold the final query string
    char query[256];

    // Use sprintf to build the query string
    snprintf(query, sizeof(query), "SELECT * FROM users WHERE username = '%s';", username);

    // Print the constructed query for demonstration purposes
    printf("Executing query: %s\n", query);

    // Here you would normally include code to actually execute the query.
    // For example, you might use a database connection and send this query.
}

int main() {
    char username[128];

    printf("Enter your username: ");
    scanf("%s", username);

    // Execute the query with the provided username
    executeQuery(username);

    return 0;
}