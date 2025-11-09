import importlib
import sys

def call_methods(class_name):
    try:
        # Dynamically load the specified module (assuming it's a Python file)
        module = importlib.import_module(class_name)
        
        # Get the class from the module
        clazz = getattr(module, class_name)
        
        # Create an instance of the class
        instance = clazz()
        
        # List all methods of the class
        method_names = [method for method in dir(instance) if callable(getattr(instance, method))]
        
        # Print available methods
        print(f"Methods for {class_name}: {', '.join(method_names)}")
        
        # Optionally, call a specific method
        while True:
            method_name = input("Enter the method name to call (or 'exit' to quit): ")
            if method_name.lower() == 'exit':
                break
            
            if hasattr(instance, method_name) and callable(getattr(instance, method_name)):
                # Call the method with no arguments for simplicity
                getattr(instance, method_name)()
            else:
                print(f"Method '{method_name}' not found or is not callable.")
    
    except ImportError:
        print(f"Module {class_name} could not be imported.")
    except AttributeError:
        print(f"Class {class_name} not found in module.")
    except Exception as e:
        print(f"An error occurred: {e}")

if __name__ == "__main__":
    if len(sys.argv) != 2:
        print("Usage: python script.py <module_name>")
    else:
        class_name = sys.argv[1]
        call_methods(class_name)
bash
python script.py myclass