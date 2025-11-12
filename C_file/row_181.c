#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <dlfcn.h>

// Function to load the plugin library and return the handle
void* load_plugin(const char* lib_path) {
    void* handle = dlopen(lib_path, RTLD_LAZY);
    if (!handle) {
        fprintf(stderr, "Error: %s\n", dlerror());
        return NULL;
    }
    return handle;
}

// Function to get a symbol from the loaded library
void* get_symbol(void* handle, const char* symbol_name) {
    void* sym = dlsym(handle, symbol_name);
    if (!sym) {
        fprintf(stderr, "Error: %s\n", dlerror());
    }
    return sym;
}

int main(int argc, char* argv[]) {
    if (argc < 2) {
        fprintf(stderr, "Usage: %s <library_path>\n", argv[0]);
        return EXIT_FAILURE;
    }

    const char* lib_path = argv[1];
    void* handle = load_plugin(lib_path);
    if (!handle) {
        return EXIT_FAILURE;
    }

    // Example: Assume the plugin exports a function named "my_function"
    void (*my_function)(void) = get_symbol(handle, "my_function");
    if (my_function) {
        my_function();  // Call the function from the plugin
    } else {
        fprintf(stderr, "Error: Symbol 'my_function' not found in library.\n");
        dlclose(handle);
        return EXIT_FAILURE;
    }

    if (dlclose(handle) != 0) {
        perror("dlclose");
        return EXIT_FAILURE;
    }

    return EXIT_SUCCESS;
}
sh
gcc -o plugin_loader main.c -ldl
sh
./plugin_loader /path/to/yourlib.so