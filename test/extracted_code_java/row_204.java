import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
@RestController
public class NativeLibraryUploader {

    public static void main(String[] args) {
        SpringApplication.run(NativeLibraryUploader.class, args);
    }

    @PostMapping("/upload")
    public String uploadLibrary(@RequestParam("file") MultipartFile file) throws Exception {
        Path path = Paths.get("C:/temp/native-libs/" + file.getOriginalFilename());
        Files.copy(file.getInputStream(), path);
        return "Library uploaded successfully!";
    }
}
sh
java -Djava.library.path=C:/temp/native-libs your.main.class
public class Main {
    public static void main(String[] args) {
        String libraryPath = "C:/temp/native-libs"; // Path to the directory where libraries are stored
        System.setProperty("jna.library.path", libraryPath);
        System.loadLibrary("your_native_lib"); // Replace with your actual native library name
    }
}
public class Main {
    public static void main(String[] args) {
        String libraryPath = "C:/temp/native-libs"; // Path to the directory where libraries are stored
        System.setProperty("jna.library.path", libraryPath);
        
        // Load the native library
        System.loadLibrary("your_native_lib"); // Replace with your actual native library name

        // Your main logic here
    }
}
#include <jni.h>
#include <stdio.h>

JNIEXPORT void JNICALL Java_Main_hello(JNIEnv *env, jobject obj) {
    printf("Hello from C!\n");
}
sh
javac Main.java
javah -jni Main
gcc -shared -fpic -o libyour_native_lib.so Main.c