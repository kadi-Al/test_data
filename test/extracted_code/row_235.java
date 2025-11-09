import java.util.Scanner;

public class DynamicLibraryLoader {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the path to the native library:");
        String libraryPath = scanner.nextLine();
        
        // Load the native library
        try {
            System.load(libraryPath);
        } catch (UnsatisfiedLinkError e) {
            System.err.println("Failed to load the specified library: " + e.getMessage());
            return;
        }

        // Assuming the library has a function named 'myNativeFunction' that you want to call
        myNativeFunction();
    }

    // Example native method (make sure this is declared in a .c or .cpp file and compiled as a native library)
    public static native void myNativeFunction();
}
#include <jni.h>
#include <stdio.h>

// Declare the function for JNI
JNIEXPORT void JNICALL Java_DynamicLibraryLoader_myNativeFunction(JNIEnv *env, jobject obj) {
    printf("Hello from native code!\n");
}
public class DynamicLibraryLoader {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the path to the native library:");
        String libraryPath = scanner.nextLine();
        
        // Load the native library
        try {
            System.load(libraryPath);
        } catch (UnsatisfiedLinkError e) {
            System.err.println("Failed to load the specified library: " + e.getMessage());
            return;
        }

        // Call the native function
        myNativeFunction();
    }

    // Declare the native method
    public static native void myNativeFunction();
}
sh
gcc -shared -o mylib.so mylib.c -fPIC
javac DynamicLibraryLoader.java
sh
gcc -shared -o mylib.dll mylib.c
javac DynamicLibraryLoader.java