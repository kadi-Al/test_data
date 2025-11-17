import select

def wait_for_input():
    while True:
        # Create a list containing stdin (file descriptor 0)
        inputs = [0]
        
        # Use the select function to check for input on stdin
        readable, writable, exceptional = select.select(inputs, [], [])
        
        if readable:
            # If there's data available on stdin, read it
            user_input = input()
            print("User Input:", user_input)
        else:
            break  # Exit the loop if no more input is available

if __name__ == "__main__":
    wait_for_input()