bash
mvn archetype:generate -DgroupId=com.example -DartifactId=JavaCodeRunner -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
cd JavaCodeRunner
xml
<dependencies>
    <dependency>
        <groupId>com.googlecode.java-diff-utils</groupId>
        <artifactId>diffutils</artifactId>
        <version>1.3.0</version>
    </dependency>
    <dependency>
        <groupId>org.apache.commons</groupId>
        <artifactId>commons-lang3</artifactId>
        <version>3.12.0</version>
    </dependency>
    <dependency>
        <groupId>com.github.spotbugs</groupId>
        <artifactId>spotbugs-annotations</artifactId>
        <version>4.7.3</version>
    </dependency>
    <!-- Java Compiler API -->
    <dependency>
        <groupId>org.openjdk.jol</groupId>
        <artifactId>jol-core</artifactId>
        <version>0.16</version>
    </dependency>
    <dependency>
        <groupId>javax.lang.model</groupId>
        <artifactId>javax.lang.model-api</artifactId>
        <version>2.5.0</version>
    </dependency>
</dependencies>
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

@SpringBootApplication
public class JavaCodeRunnerApplication {
    public static void main(String[] args) {
        SpringApplication.run(JavaCodeRunnerApplication.class, args);
    }
}

@RestController
class CodeController {
    @PostMapping("/compile")
    public String compileAndRun(@RequestBody String code) throws IOException {
        File tempFile = Files.createTempFile("temp", ".java").toFile();
        tempFile.deleteOnExit();
        Files.write(tempFile.toPath(), Arrays.asList(code));

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        javax.tools.StandardJavaFileManager fileManager = javax.tools.StandardJavaFileManager.getDefault().getJavaFileManager(null);
        compiler.getTask(null, fileManager, null, null, null, fileManager.getJavaFileObjectsFromFiles(Arrays.asList(tempFile))).call();

        // Assuming the code is simple and doesn't need external libraries or complex setup
        Process process = Runtime.getRuntime().exec("java -cp " + tempFile.getParent());
        byte[] buffer = new byte[1024];
        int exitCode = process.waitFor();
        return "Code executed with exit code: " + exitCode;
    }
}
bash
mvn spring-boot:run
url
curl -X POST http://localhost:8080/compile -H "Content-Type: application/json" -d "{code}"