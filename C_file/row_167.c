import os
import getpass

def show_system_info():
    # Get the current working directory
    cwd = os.getcwd()
    
    # Get the username using the getpass module
    username = getpass.getuser()
    
    # Display the information
    print(f"Current Working Directory: {cwd}")
    print(f"Username: {username}")

if __name__ == "__main__":
    show_system_info()