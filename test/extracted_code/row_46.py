import urllib.parse

def redirect_url(url):
    # Parse the URL
    parsed_url = urllib.parse.urlparse(url)
    
    # Get the domain name from the network location attribute of the parsed URL
    domain_name = parsed_url.netloc
    
    # Check if the domain name is example.com
    if domain_name == 'example.com':
        # Return the original URL or a specific redirect URL for example.com
        return url  # or return "https://redirected.to/example.com"
    else:
        # Return a 404 error (status code 404) or an appropriate message
        return "", 404  # Assuming you want to use HTTP status codes for the response

# Example usage:
# print(redirect_url("http://example.com"))  # Should print "http://example.com" if redirected
# print(redirect_url("http://notexample.com"))  # Should return a 404 error or message