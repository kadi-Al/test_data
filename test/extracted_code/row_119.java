bash
mvn archetype:generate -DgroupId=com.example -DartifactId=readconfigservice -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <!-- Add other necessary dependencies here -->
</dependencies>
src/main/java/com/example/readconfigservice/
                            ReadConfigServiceApplication.java
                            ConfigController.java
package com.example.readconfigservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReadConfigServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReadConfigServiceApplication.class, args);
    }
}
package com.example.readconfigservice;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
public class ConfigController {

    @RequestMapping(value = "/readConfig", method = RequestMethod.GET)
    public byte[] readConfig(@RequestParam String path) throws IOException {
        return Files.readAllBytes(Paths.get(path));
    }
}
bash
mvn spring-boot:run