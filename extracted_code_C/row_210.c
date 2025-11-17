#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>

int main() {
    const char *filename = "newfile.txt"; // Specify the filename here
    mode_t permissions = 0777; // Set the desired permissions

    // Create a new file with specified permissions
    int fd = open(filename, O_CREAT | O_WRONLY, permissions);
    
    if (fd == -1) {
        perror("Error creating file");
        return EXIT_FAILURE;
    }

    printf("File created successfully with 0777 permissions.\n");
    close(fd); // Close the file descriptor

    return EXIT_SUCCESS;
}
sh
gcc -o create_file create_file.c
./create_file