import os

# Define the safe directory path
safe_directory = '/safe/'

# Ensure the directory exists and is accessible
if not os.access(safe_directory, os.R_OK):
    raise Exception("Access denied to the directory.")

# Construct the full file path
file_path = os.path.join(safe_directory, 'your_file.txt')

try:
    # Open and read the file
    with open(file_path, 'r') as file:
        content = file.read()
        print(content)
except FileNotFoundError:
    print("The specified file does not exist in the safe directory.")
except PermissionError:
    print("You do not have permission to read this file.")