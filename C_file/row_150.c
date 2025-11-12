import os
import tempfile
import uuid

def create_vulnerable_temp_file(user_input):
    """
    Creates a temporary file with a name based on user input.
    If the filename ends with '.txt', it creates a regular file;
    Otherwise, it creates a symlink to a file named 'symlink_target'.
    """
    # Generate a unique temporary directory path
    temp_dir = os.path.join(tempfile.gettempdir(), str(uuid.uuid4()))
    
    # Ensure the directory exists and is empty
    if not os.path.exists(temp_dir):
        os.makedirs(temp_dir)
    
    # Construct the full path for the temporary file or symlink
    filename = os.path.join(temp_dir, user_input)
    
    # Check the extension to decide whether to create a symlink or a regular file
    if not user_input.endswith('.txt'):
        target = os.path.join(temp_dir, 'symlink_target')
        os.symlink('symlink_target', target)  # Create a symlink
        return target
    else:
        with open(filename, 'w') as f:
            f.write("This is a temporary file.")
        return filename

# Example usage
user_input = input("Enter the filename (with or without .txt extension): ")
file_path = create_vulnerable_temp_file(user_input)
print(f"File/Symlink created at: {file_path}")