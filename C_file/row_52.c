#include <stdio.h>
#include <string.h>

void inject_sql(char *sql, const char *userInput) {
    // Concatenate the SQL query with user input directly
    strcat(sql, " WHERE category='");
    strcat(sql, userInput);
    strcat(sql, "'");
}

int main() {
    char sql[256] = "";  // Buffer to hold the SQL query
    char userInput[100];  // Example user input

    printf("Enter a category: ");
    scanf("%s", userInput);

    inject_sql(sql, userInput);

    printf("Generated SQL Query: %s\n", sql);

    return 0;
}