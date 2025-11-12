import threading

# Define two resources (locks)
resource1 = threading.Lock()
resource2 = threading.Lock()

def thread_task(name):
    print(f"Thread {name} starting")
    
    # Thread 1 will lock resource1 first, then resource2
    if name == "Thread-1":
        with resource1:
            print(f"Thread {name} has locked resource1")
            # Introduce a delay to increase the chance of deadlock
            threading.Event().wait(0.1)
            with resource2:
                print(f"Thread {name} has locked resource2")
    # Thread 2 will lock resource2 first, then resource1
    elif name == "Thread-2":
        with resource2:
            print(f"Thread {name} has locked resource2")
            # Introduce a delay to increase the chance of deadlock
            threading.Event().wait(0.1)
            with resource1:
                print(f"Thread {name} has locked resource1")

# Create and start threads
thread1 = threading.Thread(target=thread_task, args=("Thread-1",))
thread2 = threading.Thread(target=thread_task, args=("Thread-2",))

thread1.start()
thread2.start()

thread1.join()
thread2.join()

print("All threads finished executing")