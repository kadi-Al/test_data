pp
#include <iostream>
#include <cstring> // For strcpy and strlen
#include <cstdlib>  // For malloc and free

int main() {
    const char* data = "data";
    size_t length = strlen(data) + 1; // Include space for the null terminator

    // Allocate memory using malloc, which is available in C but not standard C++
    void* memory = malloc(length);
    if (memory == nullptr) {
        std::cerr << "Memory allocation failed!" << std::endl;
        return 1;
    }

    // Copy the string into the allocated memory
    strcpy((char*)memory, data);

    // Print the content of the copied memory to verify
    std::cout << "Copied string: " << (char*)memory << std::endl;

    // Free the allocated memory
    free(memory);

    return 0;
}
pp
#include <iostream>
#include <string> // For std::string and std::strlen

int main() {
    const char* data = "data";
    size_t length = std::strlen(data) + 1; // Include space for the null terminator

    // Allocate memory using new, which is standard C++
    std::unique_ptr<char[]> memory(new char[length]);
    if (memory == nullptr) {
        std::cerr << "Memory allocation failed!" << std::endl;
        return 1;
    }

    // Copy the string into the allocated memory
    std::strcpy(memory.get(), data);

    // Print the content of the copied memory to verify
    std::cout << "Copied string: " << memory.get() << std::endl;

    return 0;
}