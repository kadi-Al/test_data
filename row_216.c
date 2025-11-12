#include <windows.h>
#include <stdio.h>

int main() {
    // Define a pointer to the DLL handle
    HMODULE hDll;

    // Try to load the DLL from the default system paths
    hDll = LoadLibrary("mydll.dll");
    if (hDll == NULL) {
        printf("Failed to load DLL\n");
        return 1;
    } else {
        printf("DLL loaded successfully!\n");
    }

    // You can now call functions from the DLL using GetProcAddress and then cast them as needed.
    // For example, if mydll.dll has a function MyFunction(), you would do:
    // FARPROC func = GetProcAddress(hDll, "MyFunction");
    // if (func == NULL) {
    //     printf("Function not found in DLL\n");
    //     return 1;
    // }

    // Remember to free the loaded DLL when it's no longer needed.
    FreeLibrary(hDll);
    return 0;
}
#include <stdio.h>
#include <stdlib.h>
#include <dlfcn.h>

int main() {
    void *handle;
    void (*function)();
    char *error;

    // Open the shared library
    handle = dlopen("./mylib.so", RTLD_LAZY);
    if (!handle) {
        fprintf(stderr, "Error loading library: %s\n", dlerror());
        exit(EXIT_FAILURE);
    }

    // Clear any existing error
    dlerror();

    // Get a symbol (function) from the library
    *(void **)(&function) = dlsym(handle, "myFunction");
    if ((error = dlerror()) != NULL)  {
        fprintf(stderr, "Error locating function: %s\n", error);
        exit(EXIT_FAILURE);
    }

    // Call the function from the library
    function();

    // Close the shared library
    dlclose(handle);

    return 0;
}