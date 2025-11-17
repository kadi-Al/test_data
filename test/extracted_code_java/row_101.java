import java.util.Base64;

public class JwtParser {
    public static void main(String[] args) {
        String jwtToken = "your.jwt.token.here"; // Replace with your actual JWT token

        try {
            // Extract the base64-encoded payload part of the JWT token
            String encodedPayload = jwtToken.split("\\.")[1];

            // Decode the Base64 string to get the original byte array
            byte[] decodedBytes = Base64.getDecoder().decode(encodedPayload);

            // Convert the byte array back to a String (though it will be in its original binary form)
            String payloadString = new String(decodedBytes);

            System.out.println("Decoded Payload: " + payloadString);
        } catch (Exception e) {
            System.err.println("Error parsing JWT token: " + e.getMessage());
        }
    }
}