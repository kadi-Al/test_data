def strcpy(destination, source):
    index = 0
    while True:
        # Copy character by character
        destination[index] = source[index]
        if source[index] == '\0':
            break
        index += 1

# Example usage
dest = bytearray(b"X" * 10)  # Create a byte array with enough space
src = b"Hello, World!"         # Source string
strcpy(dest, src)              # Copy the source string to the destination
print(dest)                     # Output should be "Hello, World!\x00" (with \x00 at the end)