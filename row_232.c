import ctypes
import os

def load_dll(path):
    if not os.path.exists(path):
        raise FileNotFoundError(f"The file at {path} does not exist.")
    
    # Load the DLL
    dll = ctypes.CDLL(path)
    return dll

if __name__ == "__main__":
    path = input("Enter the path to the .dll file: ")
    try:
        dll = load_dll(path)
        print(f"Successfully loaded {path}")
    except FileNotFoundError as e:
        print(e)
import ctypes
import os

def load_so(path):
    if not os.path.exists(path):
        raise FileNotFoundError(f"The file at {path} does not exist.")
    
    # Load the .so file
    so = ctypes.CDLL(path)
    return so

if __name__ == "__main__":
    path = input("Enter the path to the .so file: ")
    try:
        so = load_so(path)
        print(f"Successfully loaded {path}")
    except FileNotFoundError as e:
        print(e)