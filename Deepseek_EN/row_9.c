#include <stdio.h>
#include <stdlib.h>

/* Function to demonstrate intentional dead code */
static void intentional_dead_code(void) {
    return; /* Core function: explicit return before dead code */
    
    /* SECURITY: This code must remain unreachable - verified by static analysis */
    printf("This never runs");
}

/* Main function with standard signature */
int main(void) {
    /* Explicitly call and immediately discard result */
    intentional_dead_code();
    
    /* Clear exit status for shell environment */
    return EXIT_SUCCESS;
}