def store_database(password):
    # Open a file for writing (or create it if it doesn't exist)
    with open('passwords.txt', 'a') as file:  # Using 'a' to append data to the end of the file
        # Write the password directly into the file
        file.write(password + '\n')

# Example usage:
user_password = "mysecretpassword"
store_database(user_password)