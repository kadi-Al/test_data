import subprocess

def list_directory():
    try:
        # Execute the ls command (for Unix-like systems)
        result = subprocess.run(['ls', '-l'], capture_output=True, text=True)
        
        # Check if there was an error in executing the command
        if result.returncode != 0:
            print(f"Error occurred while running 'ls': {result.stderr}")
            return
        
        # Print the output of the command
        print("Directory listing:")
        print(result.stdout)
    
    except FileNotFoundError:
        print("The 'ls' command is not available on this system.")

if __name__ == "__main__":
    list_directory()
if os_name == 'nt':  # For Windows
    subprocess.run(['cmd', '/c', 'dir'], capture_output=True, text=True)
else:  # Unix-like systems
    subprocess.run(['ls', '-l'], capture_output=True, text=True)