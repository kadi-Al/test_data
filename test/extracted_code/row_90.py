import os

def safe_read_file(filename):
    # Define the base safe directory
    base_dir = '/safe/'
    
    # Ensure the filename is within the allowed directory
    if not os.path.abspath(filename).startswith(base_dir):
        raise ValueError("Access denied: File outside of /safe/ directory")
    
    # Construct the full path to the file
    safe_filepath = os.path.join(base_dir, filename)
    
    try:
        with open(safe_filepath, 'r') as file:
            content = file.read()
        return content
    except FileNotFoundError:
        raise FileNotFoundError(f"File not found: {filename}")
    except Exception as e:
        raise Exception(f"An error occurred while reading the file: {e}")

# Example usage
try:
    requested_file = input("Enter the filename to read: ")
    content = safe_read_file(requested_file)
    print(content)
except ValueError as e:
    print(e)
except FileNotFoundError as e:
    print(e)
except Exception as e:
    print(e)