def process(index):
    # Example function to process each index, replace with actual processing logic
    print(f"Processing index: {index}")

try:
    user_input = int(input("Enter a number: "))
    if user_input < 0:
        raise ValueError("Input must be non-negative.")
    
    for i in range(user_input):
        process(i)
except ValueError as e:
    print(f"Error: {e}")