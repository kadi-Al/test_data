pp
#include <cstdint> // For std::uint32_t
#include <cstdlib> // For malloc

void createMultiplicationOverflow(std::uint32_t width, std::uint32_t height) {
    if (width == 0 || height == 0) {
        throw std::invalid_argument("Width and height must be non-zero.");
    }

    // Calculate the size of memory to allocate for the image.
    std::size_t size = width * height;

    // Allocate memory using malloc (which returns void*).
    void* ptr = malloc(size);
    if (!ptr) {
        throw std::bad_alloc();
    }

    // You might want to do something with the allocated pointer, e.g., free it later.
    // For example:
    // ... some operations using ptr ...

    // Free the allocated memory when done.
    free(ptr);
}