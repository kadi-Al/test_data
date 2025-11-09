xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>
</dependencies>
package com.example.upload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class UploadApplication {
    public static void main(String[] args) {
        SpringApplication.run(UploadApplication.class, args);
    }
}

@RestController
class FileUploadController {
    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();
            // Specify the directory where you want to save the uploaded files
            File dir = new File("./uploads");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
            java.nio.file.Files.write(serverFile.toPath(), bytes);
            return "You successfully uploaded '" + file.getOriginalFilename() + "'";
        } else {
            return "File is empty!";
        }
    }
}
public class Person {
    private String name;
    private int age;

    // Constructor
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for age
    public void setAge(int age) {
        this.age = age;
    }

    // Getter for age
    public int getAge() {
        return age;
    }
}
public class Main {
    public static void main(String[] args) {
        // Create a new Person object with name "Alice" and age 30
        Person alice = new Person("Alice", 30);

        // Print the person's details
        System.out.println("Name: " + alice.getName());
        System.out.println("Age: " + alice.getAge());

        // Update the person's name to "Bob" and age to 25
        alice.setName("Bob");
        alice.setAge(25);

        // Print the updated details
        System.out.println("Updated Name: " + alice.getName());
        System.out.println("Updated Age: " + alice.getAge());
    }
}