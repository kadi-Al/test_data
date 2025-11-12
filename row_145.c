def main():
    # Define the correct prefix
    correct_prefix = "user_"
    
    # Prompt the user to enter a password
    password = input("Please enter your password: ")
    
    # Check if the password starts with the correct prefix
    if password.startswith(correct_prefix):
        print("Password accepted!")
    else:
        print("Incorrect prefix. Password must start with '{}'.".format(correct_prefix))

if __name__ == "__main__":
    main()