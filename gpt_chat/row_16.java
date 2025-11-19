import javax.net.ssl.*;
import java.security.KeyStore;
import java.security.cert.X509Certificate;
import java.io.InputStream;
import java.io.FileInputStream;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateException;
import javax.net.ssl.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class SecureHttpClient {

    public static void main(String[] args) throws Exception {
        // Ensure to use the latest version of HttpClient introduced in Java 11
        HttpClient client = HttpClient.newBuilder()
                .sslContext(createSSLContext())
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://example.com"))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }

    private static SSLContext createSSLContext() throws Exception {
        // Load CA certificate
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        InputStream caInput = new FileInputStream("path/to/cacert.pem");
        X509Certificate caCert = (X509Certificate) cf.generateCertificate(caInput);

        // Create a KeyStore containing the CA certificate
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(null, null);
        keyStore.setCertificateEntry("caCert", caCert);

        // Trust manager that trusts the CA certificate in our KeyStore
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(keyStore);

        // SSLContext with custom trust manager
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, tmf.getTrustManagers(), null);

        return sslContext;
    }
}