#include <stdio.h>
#include <stdlib.h>
#include <dlfcn.h>  // For dlopen, dlsym, and dlclose

// Function prototypes for the functions you want to call from the library
typedef void (*FunctionType)();

int main() {
    char libName[256];
    printf("Enter the name of the shared library: ");
    scanf("%s", libName);

    // Open the dynamic library
    void *handle = dlopen(libName, RTLD_LAZY);
    if (!handle) {
        fprintf(stderr, "Error opening library: %s\n", dlerror());
        return EXIT_FAILURE;
    }

    // Clear any existing error
    dlerror();

    // Load a function from the library (assuming it exports a function named 'myFunction')
    FunctionType myFunction = (FunctionType)dlsym(handle, "myFunction");
    const char *dlsym_error = dlerror();
    if (dlsym_error) {
        fprintf(stderr, "Error loading symbol: %s\n", dlsym_error);
        dlclose(handle);
        return EXIT_FAILURE;
    }

    // Call the function from the library
    myFunction();

    // Close the library
    dlclose(handle);

    return EXIT_SUCCESS;
}
sh
gcc -o load_lib your_program.c -ldl