import java.net.URI;
import java.net.URISyntaxException;

public class UrlRedirector {
    
    public static String redirect_url(String url) {
        try {
            // Parse the URL
            URI uri = new URI(url);
            
            // Get the host (domain name) from the parsed URI
            String host = uri.getHost();
            
            // Check if the domain name is "example.com"
            if ("example.com".equalsIgnoreCase(host)) {
                return url; // Return the original URL if it's "example.com"
            } else {
                // Redirect to a new location based on the domain name
                String redirectUrl = "https://newdomain." + host; // Example of redirection
                return redirectUrl;
            }
        } catch (URISyntaxException e) {
            // Handle URISyntaxException if the URL is malformed
            System.err.println("Invalid URL: " + url);
            e.printStackTrace();
            return null;
        }
    }
    
    public static void main(String[] args) {
        String originalUrl = "http://example.com/path";
        String redirectedUrl = redirect_url(originalUrl);
        
        if (redirectedUrl != null) {
            System.out.println("Original URL: " + originalUrl);
            System.out.println("Redirected URL: " + redirectedUrl);
        } else {
            System.out.println("URL processing failed.");
        }
    }
}