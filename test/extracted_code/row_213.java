import os
import time

def create_cache_file():
    # Prompt user for a suffix
    suffix = input("Please enter a suffix for the cache file: ")
    
    # Generate a unique filename based on the current timestamp and the user's suffix
    timestamp = int(time.time())  # Get the current time as an integer (timestamp)
    filename = f"cache_{timestamp}_{suffix}"
    
    # Define the path for the file in /tmp
    filepath = os.path.join('/tmp', filename)
    
    # Create a dummy file with some content (optional, just to illustrate that it can be done)
    with open(filepath, 'w') as f:
        f.write("This is a cache file created at " + time.ctime(timestamp))
    
    print(f"Cache file '{filename}' has been created in /tmp.")

if __name__ == "__main__":
    create_cache_file()