import os
import os.path
import stat

def check_permissions(file_path):
    """Check if the file exists and has read/write permissions."""
    if not os.path.exists(file_path):
        raise FileNotFoundError(f"The file {file_path} does not exist.")
    
    if not os.access(file_path, os.R_OK | os.W_OK):
        raise PermissionError(f"Permission denied to access the file {file_path}.")

def read_file(file_path):
    """Reads the contents of a file if it exists and has permissions."""
    try:
        check_permissions(file_path)
        with open(file_path, 'r') as file:
            return file.read()
    except (FileNotFoundError, PermissionError) as e:
        print(e)
        return None

def write_file(file_path, data):
    """Writes data to a file if it exists and has permissions."""
    try:
        check_permissions(file_path)
        with open(file_path, 'w') as file:
            file.write(data)
    except (FileNotFoundError, PermissionError) as e:
        print(e)

# Example usage
if __name__ == "__main__":
    file_path = "example.txt"
    
    # Check if the file exists and has read/write permissions
    try:
        check_permissions(file_path)
        print("File exists and has read/write permissions.")
        
        # Read from the file
        content = read_file(file_path)
        if content is not None:
            print(f"Content of {file_path}: {content}")
        
        # Write to the file
        new_data = "Hello, world!"
        write_file(file_path, new_data)
        print(f"Wrote '{new_data}' to {file_path}.")
    
    except (FileNotFoundError, PermissionError) as e:
        print(e)