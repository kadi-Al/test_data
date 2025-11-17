import os
import uuid
import stat

def create_temp_file(user_id):
    # Generate a unique temporary filename based on user ID and some randomness
    temp_filename = f"/tmp/appdata_{user_id}_{uuid.uuid4().hex}"
    
    # Create an empty file with the generated name
    with open(temp_filename, 'w') as f:
        pass
    
    # Set the mode of the file to be world-readable (chmod 0644)
    os.chmod(temp_filename, stat.S_IRUSR | stat.S_IWUSR | stat.S_IRGRP | stat.S_IROTH)
    
    return temp_filename

# Example usage
user_id = "12345"
file_path = create_temp_file(user_id)
print(f"Created temporary file: {file_path}")
import os
import tempfile
import stat

def create_secure_temp_file(user_id):
    # Create a temporary file with controlled permissions
    fd, temp_filename = tempfile.mkstemp(prefix=f"appdata_{user_id}_", dir="/tmp")
    
    # Close the file descriptor immediately to make it accessible
    os.close(fd)
    
    # Set the mode of the file to be world-readable (chmod 0644)
    os.chmod(temp_filename, stat.S_IRUSR | stat.S_IWUSR | stat.S_IRGRP | stat.S_IROTH)
    
    return temp_filename

# Example usage
user_id = "12345"
file_path = create_secure_temp_file(user_id)
print(f"Created temporary file: {file_path}")