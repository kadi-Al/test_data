properties
# Configuration for multipart uploads
spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=10MB
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import javax.tools.*;
import com.sun.source.util.JavacTask;
import com.sun.source.util.SourceVersion;
import java.net.URL;
import java.net.URLClassLoader;

@RestController
@RequestMapping("/classes")
public class ClassDefinitionController {

    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public String uploadClass(@RequestParam("file") MultipartFile file) throws IOException, IllegalAccessException, InstantiationException {
        if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();
            String javaCode = new String(bytes, StandardCharsets.UTF_8);

            // Compile and load the class dynamically
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            JavaFileObject javaFileObject = new InMemoryJavaFileObject(file.getName(), javaCode);
            DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
            StandardJavaFileManager standardFileManager = compiler.getStandardFileManager(diagnostics, null, null);

            JavacTask task = (JavacTask) compiler.getTask(null, standardFileManager, diagnostics, null, null, javaFileObject);
            boolean success = task.call();

            if (!success) {
                StringBuilder errorMessage = new StringBuilder();
                for (Diagnostic<? extends JavaFileObject> diagnostic : diagnostics.getDiagnostics()) {
                    errorMessage.append(diagnostic.toString()).append("\n");
                }
                throw new RuntimeException("Compilation failed: " + errorMessage.toString());
            }

            // Load the compiled class
            URL[] urls = {new URL("file:///tmp/")}; // Adjust the path as needed
            URLClassLoader classLoader = new URLClassLoader(urls);
            Class<?> clazz = null;
            try {
                clazz = classLoader.loadClass("com.example." + file.getOriginalFilename().split("\\.")[0]);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("Failed to load the compiled class", e);
            }

            // Optionally, instantiate and use the class
            Object instance = clazz.newInstance();
            Method method = clazz.getMethod("main", String[].class);
            method.invoke(instance, (Object) new String[]{});

            return "Class uploaded and defined successfully.";
        } else {
            return "File is empty";
        }
    }
}
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import java.io.IOException;
import java.net.URI;

public class InMemoryJavaFileObject extends SimpleJavaFileObject {
    private final String sourceCode;

    protected InMemoryJavaFileObject(String className, String sourceCode) {
        super(URI.create("string:///" + className.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
        this.sourceCode = sourceCode;
    }

    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
        return sourceCode;
    }
}