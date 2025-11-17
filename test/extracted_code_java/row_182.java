import time

def fixed_time_compare(val1, val2):
    """
    Compare two strings in a way that doesn't leak timing information.
    """
    if len(val1) != len(val2):
        return False
    
    result = 0
    for x, y in zip(val1, val2):
        result |= ord(x) ^ ord(y)
    
    # If the strings are identical, result will be 0. Otherwise, it will be non-zero.
    return result == 0

# Example usage:
password_attempt = "userinput"
correct_password = "correctpass"

if fixed_time_compare(password_attempt, correct_password):
    print("Authentication successful!")
else:
    print("Authentication failed!")