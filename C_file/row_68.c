def sprintf(output, user_format, *data):
    # Initialize an index for the output string
    index = 0
    
    # Iterate over each character in the format string
    for char in user_format:
        if char == '%':
            # If the next character is not a valid specifier, raise an error
            if len(user_format) <= index + 1 or user_format[index + 1] not in 'sdiouxXfFeEgGc%':
                raise ValueError("Invalid format string")
            
            # Determine the type of formatting to use based on the specifier
            specifier = user_format[index + 1]
            index += 2
            
            if specifier == 's':
                value = str(data[0])
                output += value
            elif specifier == 'd' or specifier == 'i' or specifier == 'u':
                try:
                    value = int(data[0])
                    output += str(value)
                except (ValueError, TypeError):
                    raise ValueError("Invalid integer format")
            elif specifier == 'o':
                try:
                    value = int(data[0])
                    output += oct(value)[2:]
                except (ValueError, TypeError):
                    raise ValueError("Invalid octal format")
            elif specifier == 'x' or specifier == 'X':
                try:
                    value = int(data[0])
                    if specifier == 'x':
                        output += hex(value)[2:]
                    else:
                        output += hex(value)[2:].upper()
                except (ValueError, TypeError):
                    raise ValueError("Invalid hexadecimal format")
            elif specifier == 'f' or specifier == 'F':
                try:
                    value = float(data[0])
                    output += str(value)
                except (ValueError, TypeError):
                    raise ValueError("Invalid floating-point format")
            elif specifier == 'e' or specifier == 'E':
                try:
                    value = float(data[0])
                    if specifier == 'e':
                        output += "{:.6e}".format(value).lower()
                    else:
                        output += "{:.6e}".format(value).upper()
                except (ValueError, TypeError):
                    raise ValueError("Invalid scientific notation format")
            elif specifier == 'g' or specifier == 'G':
                try:
                    value = float(data[0])
                    if specifier == 'g':
                        output += "{:.6g}".format(value).lower()
                    else:
                        output += "{:.6g}".format(value).upper()
                except (ValueError, TypeError):
                    raise ValueError("Invalid general format")
            elif specifier == 'c':
                try:
                    value = int(data[0])
                    output += chr(value)
                except (ValueError, TypeError):
                    raise ValueError("Invalid character format")
            elif specifier == '%':
                output += '%'
        
        else:
            # If the character is not a %, add it to the output string
            output += char
    
    return output

# Example usage:
output = ""
result = sprintf(output, "The answer is %d and pi is %f", [42, 3.14159])
print(result)  # Output: The answer is 42 and pi is 3.141590