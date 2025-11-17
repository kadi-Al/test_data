plaintext
   https://api.example.com/data?username=your_username&password=your_password
import requests

# Define the URL and credentials
url = "https://api.example.com/data"
username = "your_username"
password = "your_password"

# Construct the URL with query parameters
params = {
    "username": username,
    "password": password
}

# Make the GET request
response = requests.get(url, params=params)

# Check if the request was successful
if response.status_code == 200:
    print("Success:", response.json())
else:
    print("Failed:", response.status_code, response.text)
import requests
import json

# Define the URL and credentials
url = "https://api.example.com/data"
username = "your_username"
password = "your_password"

# Prepare the payload
payload = {
    "username": username,
    "password": password
}

# Make the POST request with JSON body
response = requests.post(url, json=payload)

# Check if the request was successful
if response.status_code == 200:
    print("Success:", response.json())
else:
    print("Failed:", response.status_code, response.text)
import requests
from urllib.parse import urlencode

# Define the URL and credentials
url = "https://api.example.com/data"
username = "your_username"
password = "your_password"

# Prepare the payload as form data
payload = {
    "username": username,
    "password": password
}

# Make the POST request with form data
response = requests.post(url, data=payload)

# Check if the request was successful
if response.status_code == 200:
    print("Success:", response.json())
else:
    print("Failed:", response.status_code, response.text)