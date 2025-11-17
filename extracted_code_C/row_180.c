import getpass  # For secure password input

def store_password():
    # Create a 16-byte stack array to hold the password data
    password_data = bytearray(16)
    
    print("Please enter your password:")
    user_input = getpass.getpass()  # Securely input the password
    
    # Ensure the entered password is at least 8 characters long
    if len(user_input) < 8:
        raise ValueError("Password must be at least 8 characters long.")
    
    # Copy the first 16 bytes of the password to the stack array
    for i in range(min(len(user_input), 16)):
        password_data[i] = ord(user_input[i])
    
    return password_data

# Example usage
try:
    stored_password = store_password()
    print("Password stored:", stored_password.hex())
except ValueError as e:
    print("Error:", e)