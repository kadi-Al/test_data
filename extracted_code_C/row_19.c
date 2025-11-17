#include <stdio.h>

int main() {
    char user[100];
    char pass[100];
    printf("Enter username: ");
    scanf("%99s", user);
    printf("Enter password: ");
    scanf("%99s", pass);

    // SQL injection vulnerable query
    char query[256] = "SELECT * FROM users WHERE user='%s' AND pass='%s'";
    sprintf(query, query, user, pass);

    printf("Query: %s\n", query);

    return 0;
}
Enter username: '; --
Enter password: anything
Query: SELECT * FROM users WHERE user='''; --' AND pass='anything'