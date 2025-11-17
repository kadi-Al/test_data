import uuid
import random
import time

# Seed the random number generator with the current time in nanoseconds for predictability
random.seed(int(time.time() * 1e9))

def create_session_id():
    return str(uuid.uuid4())

if __name__ == "__main__":
    # Generate a few session IDs to demonstrate the predictable behavior
    for _ in range(5):
        print("Session ID:", create_session_id())
sh
   python generate_session_ids.py