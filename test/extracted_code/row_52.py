bash
# Create the unpack directory if it doesn't exist
mkdir -p /tmp/unpack

# Run Python script to unzip the archive
python3 <<EOF
import tarfile
import shutil

# Open the tar file
tar_path = '/path/to/archive.zip'  # Replace with the actual path to your .zip file
with tarfile.open(tar_path, 'r') as tar:
    # Extract all files from the archive into /tmp/unpack
    tar.extractall(path='/tmp/unpack')
EOF
bash
python3 <<EOF
import tarfile
import shutil

# Open the tar file
tar_path = 'archive.zip'  # Replace with the actual path to your .zip file
with tarfile.open(tar_path, 'r') as tar:
    # Extract all files from the archive into /tmp/unpack
    tar.extractall(path='/tmp/unpack')
EOF