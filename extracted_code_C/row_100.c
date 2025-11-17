#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

// Mutexes
pthread_mutex_t a = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t b = PTHREAD_MUTEX_INITIALIZER;

void* thread1(void* arg) {
    pthread_mutex_lock(&a); // Lock mutex a
    printf("Thread 1 locked mutex A\n");
    sleep(1); // Simulate some work with delay to increase chance of deadlock
    pthread_mutex_lock(&b); // Try to lock mutex b, but thread2 has it already
    printf("Thread 1 locked mutex B\n");
    pthread_mutex_unlock(&a); // Normally unlock here in real code
    pthread_mutex_unlock(&b); // But we won't reach this due to deadlock
    return NULL;
}

void* thread2(void* arg) {
    pthread_mutex_lock(&b); // Lock mutex b
    printf("Thread 2 locked mutex B\n");
    sleep(1); // Simulate some work with delay to increase chance of deadlock
    pthread_mutex_lock(&a); // Try to lock mutex a, but thread1 has it already
    printf("Thread 2 locked mutex A\n");
    pthread_mutex_unlock(&b); // Normally unlock here in real code
    pthread_mutex_unlock(&a); // But we won't reach this due to deadlock
    return NULL;
}

int main() {
    pthread_t t1, t2;

    // Create threads
    pthread_create(&t1, NULL, thread1, NULL);
    pthread_create(&t2, NULL, thread2, NULL);

    // Wait for threads to finish (they won't due to deadlock)
    pthread_join(t1, NULL);
    pthread_join(t2, NULL);

    return 0;
}