import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpsClient {
    public static void main(String[] args) {
        try {
            // Specify the URL to connect to
            URL url = new URL("https://example.com");
            
            // Open a connection to the URL and cast it to HttpsURLConnection
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            
            // Set up the HostnameVerifier to accept all hostnames
            conn.setHostnameVerifier((hostname, session) -> true);
            
            // Create a trust manager that does not validate certificate chains
            TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return new java.security.cert.X509Certificate[0];
                    }
                    
                    @Override
                    public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {}
                    
                    @Override
                    public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {}
                }
            };
            
            // Install the all-trusting trust manager
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
            
            // Perform the request and read the response
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);
            
            try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String inputLine;
                StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                System.out.println("Response: " + content.toString());
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}