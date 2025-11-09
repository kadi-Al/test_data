import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private Properties properties = new Properties();
    private boolean useDevBackdoor = false;

    public ConfigReader(String configFilePath) {
        try (FileInputStream fis = new FileInputStream(configFilePath)) {
            properties.load(fis);
            // Load other configurations as needed
        } catch (IOException e) {
            System.err.println("Configuration file could not be loaded.");
        }
    }

    public void checkDevBackdoor() {
        if (properties.containsKey("useDevBackdoor")) {
            useDevBackdoor = Boolean.parseBoolean(properties.getProperty("useDevBackdoor"));
        }
    }

    public boolean isUseDevBackdoor() {
        return useDevBackdoor;
    }
}
properties
useDevBackdoor=true