import javax.net.ssl.*;
import java.security.KeyStore;
import java.security.cert.CertificateFactory;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.TimeUnit;

public class SecureHttpClient {
    private final boolean isDevelopmentMode;
    
    public SecureHttpClient() {
        this.isDevelopmentMode = "true".equalsIgnoreCase(System.getenv("DEV_MODE"));
    }

    public SSLSocketFactory createSslSocketFactory() throws Exception {
        if (isDevelopmentMode) {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                @Override
                public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws java.security.cert.CertificateException {
                    // Trust all certificates in development mode
                }

                @Override
                public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws java.security.cert.CertificateException {
                    // Trust all certificates in development mode
                }

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new java.security.cert.X509Certificate[]{};
                }
            }};
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            return sslContext.getSocketFactory();
        } else {
            // In production, use the default keystore and perform proper certificate chain validation
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            InputStream is = SecureHttpClient.class.getResourceAsStream("/path/to/cacerts");
            keyStore.load(is, "changeit".toCharArray()); // Default password for Java's cacerts file
            SSLContext sslContext = SSLContext.getInstance("TLS");
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init(keyStore);
            sslContext.init(null, tmf.getTrustManagers(), new java.security.SecureRandom());
            return sslContext.getSocketFactory();
        }
    }

    public HttpClient createHttpClient() throws Exception {
        SSLSocketFactory sslSocketFactory = createSslSocketFactory();
        HttpClient.Builder clientBuilder = HttpClient.newBuilder()
                .sslSocketFactory(sslSocketFactory, new X509ExtendedTrustManager())
                .hostnameVerifier((String hostname, SSLSession session) -> true); // Allow all hostnames in development mode for demo purposes
        
        if (!isDevelopmentMode) {
            clientBuilder.sslParameters(new SSLParameters()); // Configure TLS settings here (e.g., enable Cipher Suite Ordering)
        }

        return clientBuilder.build();
    }

    public static void main(String[] args) throws Exception {
        SecureHttpClient secureHttpClient = new SecureHttpClient();
        HttpClient httpClient = secureHttpClient.createHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://example.com"))
                .GET()
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }
}