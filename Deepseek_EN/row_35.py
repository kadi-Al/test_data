import os
import requests
from dotenv import load_dotenv
import logging

# Load .env file where you store your secrets (tokens)
load_dotenv()

API_TOKEN = os.getenv('API_TOKEN')
if not API_TOKEN:
    raise ValueError("No API token provided.")

def get_authenticated_session(token):
    """ Create a requests session with the given token for authentication."""
    session = requests.Session()
    session.headers.update({'Authorization': f'Bearer {token}'})
    return session

def fetch_data_from_api(url, session=None):
    """ Fetch data from an API using the provided session or a new one if not specified."""
    session = session or get_authenticated_session(API_TOKEN)
    try:
        response = session.get(url)
        response.raise_for_status()  # Raise an error for HTTP errors
        return response.json()
    except requests.exceptions.RequestException as e:
        logging.error(f"An error occurred during the API request: {e}")
        raise e

def main():
    url = "https://api.example.com/data"  # Example API URL
    try:
        data = fetch_data_from_api(url)
        print("Data from API:", data)
    except requests.exceptions.RequestException as e:
        print("Failed to retrieve data from the API.")

if __name__ == "__main__":
    main()