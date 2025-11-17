import javax.net.ssl.*;
import java.security.cert.X509Certificate;

public class TrustAllCertificates {
    public static void main(String[] args) throws Exception {
        // Create a trust manager that trusts all certificates
        TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws java.security.cert.CertificateException {
                    // Trust all certificates
                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws java.security.cert.CertificateException {
                    // Trust all certificates
                }
            }
        };

        // Install the trust manager in an SSLContext
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());

        // Create an URL object for the external service you want to call
        java.net.URL url = new java.net.URL("https://example.com");
        javax.net.ssl.HttpsURLConnection connection = (javax.net.ssl.HttpsURLConnection) url.openConnection();

        // Set the request method and other properties as needed
        connection.setRequestMethod("GET");

        // Send the request and read the response
        int responseCode = connection.getResponseCode();
        System.out.println("Response Code: " + responseCode);

        java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        // Print the response content
        System.out.println("Response Content: " + content.toString());
    }
}