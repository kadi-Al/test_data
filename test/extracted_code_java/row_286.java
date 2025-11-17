bash
   pip install requests
import os
import requests

def download_resource(url, save_path):
    try:
        response = requests.get(url)
        response.raise_for_status()  # Raises an HTTPError for bad responses (4xx and 5xx)
        
        with open(save_path, 'wb') as file:
            file.write(response.content)
        print(f"Downloaded {url} to {save_path}")
    except requests.exceptions.RequestException as e:
        print(f"Error downloading {url}: {e}")

def main():
    url = input("Enter the URL of the resource to download: ")
    
    if not os.path.exists('downloaded_resources'):
        os.makedirs('downloaded_resources')  # Create directory if it doesn't exist
    
    response = requests.get(url)
    content_type = response.headers.get('Content-Type', '')
    
    if 'image' in content_type:
        filename = os.path.join('downloaded_resources', os.path.basename(url))
        download_resource(url, filename)
    else:
        print("This script currently only supports downloading image files.")

if __name__ == "__main__":
    main()