#include <stdio.h>
#include <unistd.h> // for read()

void my_read(int fd, char *buffer, int maxlen) {
    int total_bytes_read = 0; // Keep track of the total bytes read so far
    while (total_bytes_read < maxlen) {
        int bytes_to_read = maxlen - total_bytes_read; // Calculate how many more bytes to read
        int bytes_read = read(fd, buffer + total_bytes_read, bytes_to_read); // Read into the buffer from current position
        
        if (bytes_read <= 0) { // Check for end of file or error
            break; // Exit loop if no more data can be read or an error occurred
        }
        
        total_bytes_read += bytes_read; // Update the total bytes read
    }
    
    buffer[total_bytes_read] = '\0'; // Null-terminate the string in the buffer
}

int main() {
    int fd = 0; // Use stdin for example, you can change this to any file descriptor opened by open()
    char buffer[1024]; // Buffer to hold the data read from the file
    
    my_read(fd, buffer, sizeof(buffer)); // Read into buffer until full or EOF
    printf("%s", buffer); // Print the contents of the buffer
    
    return 0;
}