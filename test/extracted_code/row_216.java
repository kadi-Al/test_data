import tempfile
import os
import uuid

def create_temp_file(user_input, shared_directory='/tmp'):
    """
    Create a temporary file with a predictable name derived from user input in a shared directory.
    
    Args:
        user_input (str): The string to be used as part of the filename.
        shared_directory (str): The directory where the temporary file will be stored.
        
    Returns:
        str: The path to the created temporary file.
    """
    # Generate a predictable UUID-based name from user input
    unique_name = uuid.uuid5(uuid.NAMESPACE_DNS, user_input)
    filename = f"{unique_name}.tmp"
    
    # Combine the shared directory with the generated filename
    temp_file_path = os.path.join(shared_directory, filename)
    
    # Create a temporary file in the specified directory
    with open(temp_file_path, 'w') as tmp_file:
        tmp_file.write("This is a temporary file created from user input.")
    
    return temp_file_path

# Example usage
user_input = "example_data"
temp_file_path = create_temp_file(user_input)
print(f"Temporary file created at: {temp_file_path}")