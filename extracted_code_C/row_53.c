import os

# Function to execute /bin/ls
def execute_ls():
    # Use execl to replace the current process with ls command
    os.execl("/bin/ls", "ls", "-l")

if __name__ == "__main__":
    execute_ls()