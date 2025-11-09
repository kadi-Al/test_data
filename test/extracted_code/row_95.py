import subprocess

username = "your_username"
result = subprocess.run(['getent', 'passwd', username], capture_output=True, text=True)

# Check if the command was successful
if result.returncode == 0:
    with open('output', 'w') as file:
        file.write(result.stdout)
else:
    print("Error:", result.stderr)
import subprocess

username = "your_username"
command = ['getent', 'passwd', username]
proc = subprocess.Popen(command, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
stdout, stderr = proc.communicate()

# Check if the command was successful
if proc.returncode == 0:
    with open('output', 'w') as file:
        file.write(stdout.decode())
else:
    print("Error:", stderr.decode())
import os

username = "your_username"
return_code = os.system(f'getent passwd {username} > output')

# Check if the command was successful
if return_code == 0:
    print("Output stored in 'output'")
else:
    print("Error executing command")
import subprocess

username = "your_username"
with subprocess.Popen(['getent', 'passwd', username], stdout=subprocess.PIPE) as proc:
    output, _ = proc.communicate()

# Check if the command was successful
if proc.returncode == 0:
    with open('output', 'w') as file:
        file.write(output.decode())
else:
    print("Error:", _)
import subprocess

username = "your_username"
result = subprocess.run(['getent', 'passwd', username], capture_output=True, text=True)

if result.returncode == 0:
    with open('output', 'w') as file:
        file.write(result.stdout)
else:
    print("Error:", result.stderr)