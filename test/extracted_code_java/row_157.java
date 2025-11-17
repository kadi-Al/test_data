import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletRequest;
import java.io.FileWriter;
import java.io.IOException;

public class LoggingHandler {
    private static final Logger logger = LoggerFactory.getLogger(LoggingHandler.class);

    public void logRequestParams(HttpServletRequest request) {
        String queryString = request.getQueryString();
        if (queryString != null) {
            StringBuilder logMessageBuilder = new StringBuilder("Params: ");
            logMessageBuilder.append(queryString);
            
            // Log the message directly without using logger.info which might escape characters
            try (FileWriter writer = new FileWriter("logfile.txt", true)) {
                writer.write(logMessageBuilder.toString());
                writer.write("\n"); // Optionally add a newline if needed
            } catch (IOException e) {
                logger.error("Failed to write to log file: ", e);
            }
        } else {
            logger.info("No query parameters found.");
        }
    }
}