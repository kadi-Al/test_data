#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

// Define two mutexes
pthread_mutex_t mutex1;
pthread_mutex_t mutex2;

void* threadFunction1(void* arg) {
    // Thread 1 tries to lock mutex1 then mutex2
    pthread_mutex_lock(&mutex1);
    printf("Thread 1 locked mutex1\n");
    
    // Sleep for a short time to allow context switch and ensure deadlock can occur
    sleep(1);
    
    pthread_mutex_lock(&mutex2);
    printf("Thread 1 locked mutex2\n");
    
    // Critical section of the thread
    printf("Thread 1 in its critical section\n");
    
    // Unlock the mutexes (this will never happen due to deadlock)
    pthread_mutex_unlock(&mutex1);
    pthread_mutex_unlock(&mutex2);
    
    return NULL;
}

void* threadFunction2(void* arg) {
    // Thread 2 tries to lock mutex2 then mutex1
    pthread_mutex_lock(&mutex2);
    printf("Thread 2 locked mutex2\n");
    
    // Sleep for a short time to allow context switch and ensure deadlock can occur
    sleep(1);
    
    pthread_mutex_lock(&mutex1);
    printf("Thread 2 locked mutex1\n");
    
    // Critical section of the thread
    printf("Thread 2 in its critical section\n");
    
    // Unlock the mutexes (this will never happen due to deadlock)
    pthread_mutex_unlock(&mutex2);
    pthread_mutex_unlock(&mutex1);
    
    return NULL;
}

int main() {
    // Initialize the mutexes
    if (pthread_mutex_init(&mutex1, NULL) != 0) {
        perror("Mutex initialization failed");
        return EXIT_FAILURE;
    }
    if (pthread_mutex_init(&mutex2, NULL) != 0) {
        perror("Mutex initialization failed");
        return EXIT_FAILURE;
    }
    
    // Create threads
    pthread_t thread1, thread2;
    if (pthread_create(&thread1, NULL, threadFunction1, NULL) != 0) {
        perror("Thread creation failed");
        return EXIT_FAILURE;
    }
    if (pthread_create(&thread2, NULL, threadFunction2, NULL) != 0) {
        perror("Thread creation failed");
        return EXIT_FAILURE;
    }
    
    // Wait for threads to finish (they won't due to deadlock)
    pthread_join(thread1, NULL);
    pthread_join(thread2, NULL);
    
    // Destroy the mutexes
    pthread_mutex_destroy(&mutex1);
    pthread_mutex_destroy(&mutex2);
    
    return EXIT_SUCCESS;
}