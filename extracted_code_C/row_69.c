#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>
#include <execinfo.h>

// Function to capture and print the stack trace
void capture_stack_trace() {
    void *buffer[100];
    size_t size = backtrace(buffer, 100);
    char **symbols = backtrace_symbols(buffer, size);
    if (symbols) {
        for (size_t i = 0; i < size; ++i) {
            fprintf(stderr, "%zu: %s\n", i, symbols[i]);
        }
        free(symbols);
    } else {
        perror("backtrace_symbols");
    }
}

// Signal handler to capture stack trace on signal (e.g., SIGINT for Ctrl+C)
void signal_handler(int signum) {
    fprintf(stderr, "Caught signal %d\n", signum);
    capture_stack_trace();
    exit(1);
}

// Example function to demonstrate the stack trace leak
void example_function() {
    // Simulate some work here
    int *null_ptr = NULL;
    *null_ptr = 42; // This will cause a segfault if uncommented
}

int main(int argc, char **argv) {
    // Register signal handler for SIGINT (Ctrl+C)
    signal(SIGINT, signal_handler);

    // Simulate some work in the main function
    example_function();

    return 0;
}
sh
gcc -o stacktrace_leak stacktrace_leak.c -rdynamic -lstdc++
sh
./stacktrace_leak