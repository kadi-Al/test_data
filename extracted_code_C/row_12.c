import os
import stat

filename = "yourfile.txt"  # Replace with your file name

# Check if the file exists and is readable
if os.path.exists(filename) and os.stat(filename).st_mode & (stat.S_IRUSR | stat.S_IRGRP | stat.S_IROTH):
    with open(filename, 'r') as file:  # Open the file in read mode
        content = file.read()
        print(content)
else:
    print("File does not exist or is not readable.")
bash
#!/bin/bash

filename="yourfile.txt"  # Replace with your file name

# Check if the file exists and is readable
if [[ -e $filename && -r $filename ]]; then
    content=$(cat "$filename")
    echo "$content"
else
    echo "File does not exist or is not readable."
fi
powershell
$filename = "yourfile.txt"  # Replace with your file name

# Check if the file exists and is readable
if (Test-Path $filename -PathType Leaf) {
    $content = Get-Content -Path $filename
    [System.Console]::WriteLine($content)
} else {
    Write-Output "File does not exist or is not readable."
}