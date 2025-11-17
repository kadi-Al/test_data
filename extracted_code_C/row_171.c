import threading

# Define two locks
lock1 = threading.Lock()
lock2 = threading.Lock()

def deadlock_function():
    # Thread 1 acquires lock1 first, then tries to acquire lock2
    def thread1_task():
        with lock1:
            print("Thread 1 acquired lock1")
            # Simulate some work
            threading.Event().wait(0.1)
            with lock2:
                print("Thread 1 acquired lock2")
    
    # Thread 2 acquires lock2 first, then tries to acquire lock1
    def thread2_task():
        with lock2:
            print("Thread 2 acquired lock2")
            # Simulate some work
            threading.Event().wait(0.1)
            with lock1:
                print("Thread 2 acquired lock1")
    
    # Create and start the threads
    thread1 = threading.Thread(target=thread1_task)
    thread2 = threading.Thread(target=thread2_task)
    
    thread1.start()
    thread2.start()
    
    # Wait for both threads to complete (which won't happen due to deadlock)
    thread1.join()
    thread2.join()

# Call the function to start the deadlock scenario
deadlock_function()