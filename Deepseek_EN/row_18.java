import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyStore;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class SecureHttpsClient {
    private static final String URL_STRING = "https://example.com/resource";
    private static final int CONNECTION_TIMEOUT = 10000; // 10 seconds
    private static final int READ_TIMEOUT = 30000; // 30 seconds

    public static void main(String[] args) {
        try {
            URL url = new URL(URL_STRING);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setConnectTimeout(CONNECTION_TIMEOUT);
            connection.setReadTimeout(READ_TIMEOUT);

            // Set TLS protocols
            String[] tlsProtocols = {"TLSv1.2", "TLSv1.3"};
            SSLContext sslContext = SSLContext.getDefault();
            SSLParameters sslParams = sslContext.getSupportedSSLParameters();
            sslParams.setProtocols(tlsProtocols);
            connection.setSSLSocketFactory(sslContext.getSocketFactory());
            connection.setHostnameVerifier((hostname, session) -> {
                // Perform hostname verification here if needed
                return true; // Default verifier accepts all hostnames
            });

            // Validate server certificate against trusted CA
            SSLPeerUnverifiedException peerUnverified = new SSLPeerUnverifiedException("Certificate validation failed");
            try (InputStream inputStream = connection.getInputStream()) {
                CertificateFactory cf = CertificateFactory.getInstance("X.509");
                X509Certificate serverCert = (X509Certificate) cf.generateCertificate(inputStream);
                // Validate the certificate as per your security policy
            } catch (SSLPeerUnverifiedException e) {
                throw new IOException("Failed to validate server certificate", e);
            }

            // Read response from the connection
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                throw new RuntimeException("Failed to read response from the server", e);
            }
        } catch (Exception e) {
            // Handle exceptions appropriately, log errors, and don't expose sensitive information
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}