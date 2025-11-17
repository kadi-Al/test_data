import os

def restore_backup(file_path):
    if not os.path.isfile(file_path):
        print("The specified file does not exist.")
        return

    try:
        with open(file_path, 'r') as file:
            data = file.read()
            print("File content:")
            print(data)
    except Exception as e:
        print(f"An error occurred while reading the file: {e}")

if __name__ == "__main__":
    user_input = input("Please enter the path to the backup file: ")
    restore_backup(user_input)
import os

SAFE_DIR = "/safe/directory"  # Define a safe directory for backups

def restore_backup(file_path):
    if not os.path.exists(file_path) or not os.path.isfile(file_path):
        print("The specified file does not exist.")
        return

    if not file_path.startswith(os.path.abspath(SAFE_DIR)):
        print("Access to the specified file is denied due to security restrictions.")
        return

    try:
        with open(file_path, 'r') as file:
            data = file.read()
            print("File content:")
            print(data)
    except Exception as e:
        print(f"An error occurred while reading the file: {e}")

if __name__ == "__main__":
    user_input = input("Please enter the path to the backup file: ")
    restore_backup(user_input)