properties
api.secret=your-api-secret-here
api.key=your-api-key-here
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApiConfig {
    @Value("${api.secret}")
    private String apiSecret;

    @Value("${api.key}")
    private String apiKey;

    public String getApiSecret() {
        return apiSecret;
    }

    public String getApiKey() {
        return apiKey;
    }
}
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiSecretController {
    @Autowired
    private ApiConfig apiConfig;

    @GetMapping("/api-secrets")
    public String displayApiSecrets() {
        return "API Secret: " + apiConfig.getApiSecret() + ", API Key: " + apiConfig.getApiKey();
    }
}
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "api")
public class ApiConfig {
    private String secret;
    private String key;

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ApiConfig.class)
public class MyApp {
    public static void main(String[] args) {
        SpringApplication.run(MyApp.class, args);
    }
}