def subtract_one_from_unsigned(number):
    if number >= 0:
        return number - 1
    else:
        print("Error: The input must be a non-negative integer.")
        return None

# Main function to interact with the user
def main():
    try:
        # Prompt the user for an unsigned integer input
        user_input = int(input("Enter an unsigned integer: "))
        
        # Subtract 1 from the provided number
        result = subtract_one_from_unsigned(user_input)
        
        # Check if the operation was successful and print the result
        if result is not None:
            print(f"The result after subtracting one is: {result}")
    except ValueError:
        # Handle the case where the user inputs a non-integer value
        print("Error: Invalid input. Please enter an unsigned integer.")

# Entry point of the program
if __name__ == "__main__":
    main()