import subprocess

def run_command(user_command):
    try:
        # Run the command using subprocess.run() with shell=True
        result = subprocess.run(user_command, shell=True)
        
        # Check if the command executed successfully
        if result.returncode == 0:
            print("Command executed successfully.")
        else:
            print(f"Command failed with return code {result.returncode}")
    except FileNotFoundError:
        print("The specified command was not found.")
    except Exception as e:
        print(f"An error occurred: {e}")

# Example usage
user_command = "ls -l"  # Replace with any command you want to run
run_command(user_command)