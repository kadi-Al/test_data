import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class UnsafePluginLoader {
    private static final String PLUGIN_CLASS_KEY = "pluginClass";

    public static void main(String[] args) throws Exception {
        // Simulate untrusted configuration library loading a map from a file
        Map<String, Object> configMap = loadConfig();

        // Get the plugin class name from the configuration
        String pluginClassName = (String) configMap.get(PLUGIN_CLASS_KEY);

        if (pluginClassName == null || pluginClassName.isEmpty()) {
            throw new IllegalArgumentException("Plugin class name is not provided.");
        }

        // Load and instantiate the plugin class via reflection
        Class<?> pluginClass = Class.forName(pluginClassName);
        Object pluginInstance = pluginClass.getDeclaredConstructor().newInstance();

        // Example usage: call a method on the plugin instance
        if (pluginInstance instanceof Plugin) {
            ((Plugin) pluginInstance).execute();
        } else {
            throw new IllegalArgumentException("The provided class does not implement the Plugin interface.");
        }
    }

    private static Map<String, Object> loadConfig() throws IOException {
        // Simulate loading a map from an untrusted configuration file
        String configContent = "{" + PLUGIN_CLASS_KEY + ": \"example.UnsafePlugin\"}";
        return new HashMap<>(parseJson(configContent));
    }

    private static Map<String, Object> parseJson(String json) {
        // Simulate parsing JSON to a map (in reality, you would use a proper JSON library)
        String[] keyValuePairs = json.substring(1, json.length() - 1).split(", ");
        Map<String, Object> configMap = new HashMap<>();
        for (String pair : keyValuePairs) {
            String[] kv = pair.split(":");
            configMap.put(kv[0].trim().replace("\"", ""), kv[1].trim());
        }
        return configMap;
    }
}

interface Plugin {
    void execute();
}

class UnsafePlugin implements Plugin {
    @Override
    public void execute() {
        System.out.println("Executing unsafe plugin!");
    }
}
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SafePluginLoader {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Plugin plugin = (Plugin) context.getBean("pluginName"); // Ensure this is a whitelisted bean name
        plugin.execute();
    }
}