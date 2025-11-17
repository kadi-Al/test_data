import jwt
from jwt import InvalidTokenError

# Example list of public keys (in practice, these should be securely managed)
KEYS = {
    "1": "-----BEGIN PUBLIC KEY-----\nMFkw....Q==\n-----END PUBLIC KEY-----",
    "2": "-----BEGIN PUBLIC KEY-----\nMFkw....R==\n-----END PUBLIC KEY-----",
}

def parse_token(token):
    try:
        # Extract the kid from the token headers
        header = jwt.get_unverified_header(token)
        if not header or 'kid' not in header:
            raise InvalidTokenError("No Key ID (kid) found in token.")
        
        kid = header['kid']
        
        # Validate the key is available
        if kid not in KEYS:
            raise InvalidTokenError(f"Key with KID {kid} not found.")
        
        # Get the corresponding public key from our fixed set of keys
        key = KEYS[kid]
        
        # Verify and decode the token using the extracted public key
        decoded_token = jwt.decode(token, key=key, algorithms=['RS256'])
        return decoded_token
    
    except InvalidTokenError as e:
        print(f"Invalid token: {e}")
        return None
    except Exception as e:
        print(f"An error occurred: {e}")
        return None

# Example usage
if __name__ == "__main__":
    token = "your.jwt.token.here"  # Replace with your JWT token
    decoded_data = parse_token(token)
    
    if decoded_data:
        print("Decoded Token Data:", decoded_data)