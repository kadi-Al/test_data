import threading

class SharedCounter:
    def __init__(self, initial_value=0):
        self._count = initial_value
        self._lock = threading.Lock()

    def increment(self):
        with self._lock:
            self._count += 1

    def get_count(self):
        return self._count

# Shared counter instance
counter = SharedCounter(0)

def thread_task(num_increments):
    for _ in range(num_increments):
        counter.increment()

# Number of threads and increments per thread
num_threads = 5
increments_per_thread = 1000

# Create and start threads
threads = []
for i in range(num_threads):
    t = threading.Thread(target=thread_task, args=(increments_per_thread,))
    threads.append(t)
    t.start()

# Wait for all threads to complete
for t in threads:
    t.join()

print("Final count:", counter.get_count())
import threading
from atomic import AtomicInt

class SharedCounter:
    def __init__(self, initial_value=0):
        self._count = AtomicInt(initial_value)

    def increment(self):
        self._count.increment()

    def get_count(self):
        return self._count.get()