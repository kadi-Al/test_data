# Define a list of vehicles
vehicles = ["Car", "Bike", "Truck", "Bus"]

# Prompt the user to enter an index
index = input("Please enter an index: ")

# Convert the entered string to an integer
try:
    index_int = int(index)
    
    # Check if the index is within the bounds of the array
    if 0 <= index_int < len(vehicles):
        # Print the vehicle at the specified index
        print("The vehicle at index", index_int, "is:", vehicles[index_int])
    else:
        print("Invalid index. Please enter a valid index within the range of 0 to", len(vehicles) - 1)
except ValueError:
    print("Please enter a valid integer index.")