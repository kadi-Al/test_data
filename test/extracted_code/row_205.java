import jwt
from datetime import datetime

def validate_token(token, issuer, audience, jwks_client):
    try:
        # Decode the token to extract claims
        decoded = jwt.decode(token, options={"verify_signature": False})
        
        # Check if 'iss' claim is present and matches expected issuer (if any)
        if 'iss' not in decoded or (issuer and decoded['iss'] != issuer):
            raise ValueError("Invalid issuer")
        
        # Check if 'aud' claim is present and matches the audience of your application
        if 'aud' not in decoded or decoded['aud'] != audience:
            raise ValueError("Invalid audience")
        
        # Check expiration time (leeway can be added to account for clock skew)
        now = datetime.utcnow()
        if 'exp' in decoded and decoded['exp'] <= now:
            raise ValueError("Token has expired")
        
        # Fetch JWKS from the issuer or a well-known endpoint
        jwks = jwks_client.get_jwks_from_issuer()
        
        # Validate the signature of the token using the fetched JWKS
        try:
            jwt.decode(token, jwks['keys'][0], algorithms=['RS256'])
        except Exception as e:
            raise ValueError("Signature verification failed") from e
        
        return decoded
    except Exception as e:
        # Handle validation errors (e.g., token not valid, issuer not trusted)
        print(f"Token validation failed: {str(e)}")
        return None

# Example usage with a mock JWKS client
def get_jwks_from_issuer():
    # Mock function to simulate fetching JWKS from the issuer
    return {
        "keys": [
            {
                "kty": "RSA",
                "e": "AQAB",
                "n": "...",
                "kid": "..."
            }
        ]
    }

# Mock JWT token, actual tokens would be received from the SSO provider
token = "your.jwt.token"
issuer = "https://example.com"
audience = "your-app-client-id"
jwks_client = {"get_jwks_from_issuer": get_jwks_from_issuer}

validated_token = validate_token(token, issuer, audience, jwks_client)
if validated_token:
    print("Token is valid and trusted")
else:
    print("Token is not trusted")