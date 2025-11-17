pp
#include <iostream>

void processPointer(int* ptr) {
    if (ptr == nullptr) { // Use nullptr in modern C++ or NULL in older code bases
        std::cout << "Pointer is null, returning from function." << std::endl;
        return;
    }
    // Here you can safely dereference the pointer and process it
    std::cout << "Processing value: " << *ptr << std::endl;
}

int main() {
    int* ptr = nullptr; // Initialize pointer to null
    processPointer(ptr); // Call function with NULL pointer

    return 0;
}
pp
#include <iostream>

void processPointer(int* ptr) {
    if (ptr == NULL) { // Use NULL for null pointers in older codebases
        std::cout << "Pointer is null, returning from function." << std::endl;
        return;
    }
    // Here you can safely dereference the pointer and process it
    std::cout << "Processing value: " << *ptr << std::endl;
}

int main() {
    int* ptr = NULL; // Initialize pointer to null
    processPointer(ptr); // Call function with NULL pointer

    return 0;
}