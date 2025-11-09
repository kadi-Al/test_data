import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

public class PluginLoader {
    private URLClassLoader pluginClassLoader;

    public PluginLoader() {
        // Create a new URLClassLoader with the system class loader as the parent
        this.pluginClassLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
    }

    public void addPlugin(String jarPath) throws MalformedURLException, Exception {
        File file = new File(jarPath);
        if (!file.exists() || !file.canRead()) {
            throw new IllegalArgumentException("Invalid or unreadable JAR file: " + jarPath);
        }

        // Add the JAR file to the classpath
        URL url = file.toURI().toURL();
        addJarToClasspath(url);
    }

    private void addJarToClasspath(URL url) throws Exception {
        // Create a new class loader with the updated classpath
        List<URL> urls = new ArrayList<>(List.of(pluginClassLoader.getURLs()));
        urls.add(url);
        URL[] arrayUrls = urls.toArray(new URL[0]);
        ClassLoader cl = new URLClassLoader(arrayUrls, pluginClassLoader.getParent());

        // Set the new class loader to be the system class loader
        Thread.currentThread().setContextClassLoader(cl);
        this.pluginClassLoader = (URLClassLoader) cl;
    }

    public static void main(String[] args) {
        try {
            PluginLoader loader = new PluginLoader();
            loader.addPlugin("path/to/your/plugin1.jar");
            loader.addPlugin("path/to/your/plugin2.jar");

            // Example of how to load a class from the plugin
            Class<?> clazz = loader.pluginClassLoader.loadClass("com.example.MyPluginClass");
            Object instance = clazz.getDeclaredConstructor().newInstance();
            System.out.println("Loaded instance: " + instance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}