sh
#!/bin/bash

# Define your username and password
USERNAME="user"
PASSWORD="pass"

# Encode the username:password combination in Base64
ENCODED=$(printf "%s:%s" "$USERNAME" "$PASSWORD" | base64)

# Print the Authorization header
printf "Authorization: Basic %s\n" "$ENCODED"
Authorization: Basic dXNlcjpwYXNz