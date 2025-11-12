import os

def create_directory(path):
    try:
        if not os.path.exists(path):
            os.makedirs(path)
            print(f"Directory {path} created successfully.")
        else:
            print(f"Directory {path} already exists.")
    except OSError as e:
        print(f"Error creating directory {path}: {e}")

# Example usage
directory_path = os.path.expanduser('~/app/data')
create_directory(directory_path)