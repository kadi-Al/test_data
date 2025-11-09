import bcrypt

# Generate a salt and hash a password
salt = bcrypt.gensalt()
hashed_password = bcrypt.hashpw(b'my_password', salt)

# Verify a password
if bcrypt.checkpw(b'my_password', hashed_password):
    print("Password is correct")
else:
    print("Password is incorrect")