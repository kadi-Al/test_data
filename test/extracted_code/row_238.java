properties
   app.apiKey=abcd1234
import org.springframework.beans.factory.annotation.Value;
   import org.springframework.context.annotation.Bean;
   import org.springframework.context.annotation.Configuration;

   @Configuration
   public class AppConfig {

       @Value("${app.apiKey}")
       private String apiKey;

       @Bean
       public void init() {
           // You can now use the apiKey variable
           System.out.println("API Key: " + apiKey);
       }
   }
sh
   export APP_APIKEY=abcd1234
public class App {
       private static final String API_KEY = System.getenv("APP_APIKEY");

       public static void main(String[] args) {
           System.out.println("API Key: " + API_KEY);
       }
   }
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
   import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
   import com.amazonaws.services.secretsmanager.model.GetSecretValueResult;

   public class App {
       private static final String API_KEY;

       static {
           AWSSecretsManager client = AWSSecretsManagerClientBuilder.defaultClient();
           GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest()
                   .withSecretId("your-secret-id");
           GetSecretValueResult getSecretValueResult = client.getSecretValue(getSecretValueRequest);
           API_KEY = getSecretValueResult.getSecretString();
       }

       public static void main(String[] args) {
           System.out.println("API Key: " + API_KEY);
       }
   }