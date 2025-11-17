import subprocess

# Command with space in the path
command = 'my program.exe'
subprocess.run(command, shell=True)  # Use shell=True to handle spaces
powershell
# Command with space in the path
Start-Process "C:\Path\To\my program.exe"
md
"C:\Path\To\my program.exe"
bash
./my_program.exe
# Incorrect way to handle a path with space without quotes
command = 'my program.exe'
subprocess.run(command)  # This will fail if there are spaces in the file path