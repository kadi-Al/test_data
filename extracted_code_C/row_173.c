def get_element(arr):
    try:
        # Read input from user
        index = int(input("Enter an integer index: "))
        
        # Check if the index is within bounds
        if 0 <= index < len(arr):
            return arr[index]
        else:
            print("Error: Index out of range!")
    except ValueError:
        print("Error: Please enter a valid integer.")
    
    return None

# Example usage
my_array = [10, 20, 30, 40, 50]
element = get_element(my_array)
if element is not None:
    print("Element at index", element)