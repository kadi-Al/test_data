bash
nano run_it
bash
touch run_it
chmod +x run_it  # Make sure to add execute permissions
bash
#!/bin/bash

# List of directories to search
SEARCH_DIRS=("/usr/local/bin" "/usr/bin" "$HOME/.local/bin")

# The program name to run
PROGRAM_NAME=$1

# Function to check if the program exists in any directory
find_program() {
    for dir in "${SEARCH_DIRS[@]}"; do
        if [ -x "$dir/$PROGRAM_NAME" ]; then
            echo "Found $PROGRAM_NAME in $dir. Executing..."
            "$dir/$PROGRAM_NAME" "$@"
            return 0
        fi
    done
    echo "Error: $PROGRAM_NAME not found." >&2
    return 1
}

# Check if a program name is provided and call the function
if [ -n "$PROGRAM_NAME" ]; then
    find_program "$@"
fi
bash
./run_it ls
bash
echo 'export PATH=$PATH:/path/to/your/scripts' >> ~/.bashrc
source ~/.bashrc  # Or source ~/.zshrc if using zsh