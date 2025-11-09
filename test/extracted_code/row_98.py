import subprocess

def run_command(cmd):
    """Execute a shell command and return its output."""
    result = subprocess.run(cmd, shell=True, capture_output=True, text=True)
    return result.stdout

# Example usage of the function with a format string
directory_contents = "ls -l"  # This is equivalent to running 'ls -l' in terminal/command prompt
output = run_command(directory_contents)
print(output)