import subprocess
import sys

def main():
    if len(sys.argv) != 2:
        print("Usage: python script.py <directory_name>")
        sys.exit(1)
    
    directory_name = sys.argv[1]
    
    try:
        # Execute the rm -rf command to delete the directory and its contents
        result = subprocess.run(['rm', '-rf', directory_name], check=True)
        print("Directory '{}' has been successfully deleted.".format(directory_name))
    except subprocess.CalledProcessError as e:
        print("An error occurred while trying to delete the directory:", e)
        sys.exit(1)

if __name__ == "__main__":
    main()
sh
   python file_cleanup_tool.py <directory_name>
sh
python file_cleanup_tool.py /path/to/directory