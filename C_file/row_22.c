#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>

// Define a global variable for the mutex
pthread_mutex_t lock;

void* threadFunc(void* arg) {
    // Lock the mutex in the function where it is needed
    pthread_mutex_lock(&lock);
    printf("Thread %ld has acquired the lock.\n", (long)arg);
    
    // Place any code that should be protected by the mutex here
    // ...

    // Normally, you would unlock the mutex before returning or exiting the function
    // However, since you mentioned "no unlock," it seems like this might be a placeholder
    // If you need to ensure the lock is always released in all code paths, consider using RAII (Resource Acquisition Is Initialization) patterns.
    
    return NULL;
}

int main() {
    pthread_t tid[5];
    int iret[5];

    // Initialize the mutex
    if (pthread_mutex_init(&lock, NULL) != 0) {
        perror("Mutex initialization failed");
        return EXIT_FAILURE;
    }

    for (long t = 0; t < 5; t++) {
        iret[t] = pthread_create(&tid[t], NULL, threadFunc, (void*)t);
        if (iret[t] != 0) {
            perror("Thread creation failed");
            return EXIT_FAILURE;
        }
    }

    for (int t = 0; t < 5; t++) {
        pthread_join(tid[t], NULL);
    }

    // Destroy the mutex when it is no longer needed
    pthread_mutex_destroy(&lock);

    return EXIT_SUCCESS;
}