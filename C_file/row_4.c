#include <stdio.h>
#include <string.h>

void safe_path(const char *userfile, char *path) {
    // Define the maximum length of the user input and the data directory
    const size_t max_len = 50;
    const char *data_dir = "./data/";

    if (strlen(userfile) >= max_len - strlen(data_dir) - 1) {
        // User file name is too long to avoid buffer overflow
        strcpy(path, "invalid"); // or handle this case as you see fit
        return;
    }

    // Construct the path safely
    snprintf(path, max_len, "%s%s", data_dir, userfile);
}

int main() {
    char userfile[50];
    char path[100]; // Ensure this is large enough to hold the constructed path

    printf("Enter a file name: ");
    scanf("%49s", userfile); // Limit input size to avoid buffer overflow

    safe_path(userfile, path);

    printf("The full path is: %s\n", path);

    return 0;
}