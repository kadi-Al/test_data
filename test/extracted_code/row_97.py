import sys
import subprocess

def check_user(username):
    # Run the 'id' command with the given username
    result = subprocess.run(['id', username], capture_output=True, text=True)
    
    # Print the exit status of the command
    print("Exit Status:", result.returncode)

if __name__ == "__main__":
    if len(sys.argv) > 1:
        username = sys.argv[1]
        check_user(username)
    else:
        print("Please provide a username as an argument.")
bash
python check_user.py <username>