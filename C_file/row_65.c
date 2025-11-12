def risky_sprintf(buffer, *args):
    # Start with the initial format string
    result = "Result: %s"
    
    # Replace the placeholder in the format string with the provided arguments
    if args:
        result = "Result: " + args[0]
    
    # Insert the formatted string into the buffer
    buffer[:] = result

# Example usage
buffer = bytearray(20)  # Assuming a buffer of size 20 for simplicity
risky_sprintf(buffer, "world")
print(buffer.decode())  # Output should be "Result: world"