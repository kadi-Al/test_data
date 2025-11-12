pp
#include <unistd.h> // For access()
#include <iostream>
#include <string>

// Function to read a file given its path
void read_file(const std::string& path) {
    // Implement your file reading logic here
    std::cout << "Reading file: " << path << std::endl;
}

int main() {
    std::string path = "example.txt"; // Replace with your file path

    if (!access(path.c_str(), R_OK)) {
        read_file(path);
    } else {
        std::cout << "File is not readable." << std::endl;
    }

    return 0;
}