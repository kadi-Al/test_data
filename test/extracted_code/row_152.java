public interface MyExtension {
    void init();
}
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

public class ExtensionClassLoader extends URLClassLoader {
    public ExtensionClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }
}
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<MyExtension> extensions = new ArrayList<>();
        String extensionDirPath = "path/to/extensions"; // Path to the directory containing JAR files

        File extensionDir = new File(extensionDirPath);
        if (extensionDir.isDirectory()) {
            File[] jarFiles = extensionDir.listFiles((dir, name) -> name.endsWith(".jar"));
            if (jarFiles != null) {
                for (File jarFile : jarFiles) {
                    try {
                        URL url = jarFile.toURI().toURL();
                        List<URL> urls = new ArrayList<>();
                        urls.add(url);
                        ExtensionClassLoader classLoader = new ExtensionClassLoader(urls.toArray(new URL[0]), null);
                        Class<?> extensionClass = classLoader.loadClass("your.package.name.ExtensionClassName");
                        MyExtension instance = (MyExtension) extensionClass.getDeclaredConstructor().newInstance();
                        extensions.add(instance);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        for (MyExtension extension : extensions) {
            extension.init();
        }
    }
}
public class MyExtensionImpl implements MyExtension {
    @Override
    public void init() {
        System.out.println("Initializing extension...");
        // Your initialization code here
    }
}
/path/to/extensions
    extension1.jar
    extension2.jar