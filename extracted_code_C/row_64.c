pp
#include <mutex>
#include <thread>
#include <iostream>

class RecursiveLock {
public:
    void lock() {
        auto tid = std::this_thread::get_id();
        if (locked_by == tid) {
            ++lock_count;
            return; // Re-entrant call, just increase the count
        }

        mutex.lock();
        locked_by = tid;
        lock_count = 1;
    }

    void unlock() {
        auto tid = std::this_thread::get_id();
        if (locked_by != tid) {
            throw std::logic_error("Attempt to unlock without holding the lock");
        }

        --lock_count;
        if (lock_count == 0) {
            mutex.unlock();
            locked_by = {}; // Reset state when no longer in use
        }
    }

private:
    std::mutex mutex;
    std::thread::id locked_by;
    int lock_count;
};

RecursiveLock rlock;

void func() {
    rlock.lock();
    std::cout << "Inside func" << std::endl;
    rlock.unlock();
}

int main() {
    std::thread t1(func);
    std::thread t2(func);

    t1.join();
    t2.join();

    return 0;
}