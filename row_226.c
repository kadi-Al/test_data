#include <stdio.h>
#include <stdlib.h>
#include <sqlite3.h>

// Callback function to handle SQL query results (not used in this example)
int callback(void *NotUsed, int argc, char **argv, char **azColName) {
    for (int i = 0; i < argc; i++) {
        printf("%s = %s\n", azColName[i], argv[i] ? argv[i] : "NULL");
    }
    printf("\n");
    return 0;
}

// Function to execute an SQL query
int exec_sql(sqlite3 *db, const char *query) {
    char *err_msg = NULL;
    int rc = sqlite3_exec(db, query, callback, NULL, &err_msg);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "SQL error: %s\n", err_msg);
        sqlite3_free(err_msg);
        return 1;
    }
    return 0;
}

int main() {
    sqlite3 *db;
    char *err_msg = NULL;

    // Open SQLite database (or create it if doesn't exist)
    int rc = sqlite3_open("test.db", &db);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "Cannot open database: %s\n", sqlite3_errmsg(db));
        sqlite3_close(db);
        return 1;
    }

    // Prompt user for table name and column names/values
    char table_name[256];
    char columns[1024] = "";
    char values[1024] = "";
    int num_columns = 0;

    printf("Enter the table name: ");
    scanf("%s", table_name);

    printf("How many columns do you want to insert into %s? ", table_name);
    scanf("%d", &num_columns);

    for (int i = 0; i < num_columns; i++) {
        char column[256];
        char value[256];
        printf("Enter the name of column %d: ", i + 1);
        scanf("%s", column);
        printf("Enter the value for column %s: ", column);
        scanf("%s", value);

        if (i > 0) {
            strcat(columns, ", ");
            strcat(values, ", ");
        }
        strcat(columns, column);
        strcat(values, "'");
        strcat(values, value);
        strcat(values, "'");
    }

    // Construct the SQL INSERT statement
    char query[2048];
    snprintf(query, sizeof(query), "INSERT INTO %s (%s) VALUES (%s);", table_name, columns, values);

    printf("Executing SQL: %s\n", query);

    // Execute the SQL query
    rc = exec_sql(db, query);
    if (rc != 0) {
        sqlite3_close(db);
        return 1;
    }

    // Close the database connection
    sqlite3_close(db);
    return 0;
}
sh
gcc -o sqlite_insert_example sqlite_insert_example.c -lsqlite3
./sqlite_insert_example