import subprocess
import sys

def get_user_info(username):
    # Execute the command to search for the username in /etc/passwd
    try:
        result = subprocess.run(['grep', username, '/etc/passwd'], capture_output=True, text=True)
        
        # Check if the grep command found any match
        if result.returncode == 0:
            print(result.stdout)
        else:
            print("User not found.")
    except FileNotFoundError:
        print("The /etc/passwd file does not exist on this system.")

if __name__ == "__main__":
    if len(sys.argv) > 1:
        username = sys.argv[1]
        get_user_info(username)
    else:
        print("Please provide a username as an argument.")
sh
   python3 get_user_info.py <username>