bash
   pip install requests
import requests
from urllib3.poolmanager import PoolManager

class IgnoreSSLContext(PoolManager):
    def __init__(self, *args, **kwargs):
        self.context = ssl._create_unverified_context()
        super(IgnoreSSLContext, self).__init__(*args, context=self.context, **kwargs)

def make_request():
    url = 'https://your-url-that-uses-ssl'
    
    try:
        response = requests.get(url, verify=False, pool_manager=IgnoreSSLContext())
        print("Response status code:", response.status_code)
        print("Response content:", response.content)
    except requests.exceptions.RequestException as e:
        print("Error making request:", str(e))

if __name__ == "__main__":
    make_request()