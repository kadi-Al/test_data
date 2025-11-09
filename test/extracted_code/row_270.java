import java.io.*;
import java.net.*;
import java.util.Base64;

public class ClassLoaderServer {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(12345);
        System.out.println("Server started on port 12345");

        while (true) {
            Socket clientSocket = serverSocket.accept();
            handleClientRequest(clientSocket);
        }
    }

    private static void handleClientRequest(Socket socket) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        // Read the Base64-encoded class data from the client
        StringBuilder encodedClassData = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            encodedClassData.append(line);
        }

        // Decode the Base64 string to byte array
        byte[] classData = Base64.getDecoder().decode(encodedClassData.toString());

        // Define the class using defineClass method of a custom ClassLoader
        MyClassLoader classLoader = new MyClassLoader();
        Class<?> clazz = classLoader.defineClass("customClassName", classData);

        // Print out the name of the defined class for demonstration purposes
        System.out.println("Defined class: " + clazz.getName());

        // Close resources
        in.close();
        out.close();
        socket.close();
    }
}

class MyClassLoader extends ClassLoader {
    protected Class<?> defineClass(String name, byte[] data) {
        return defineClass(name, data, 0, data.length);
    }
}
import base64
import socket

def send_class(host='localhost', port=12345):
    class_data = b'\n\x89PNG\r\n\x1a\n'  # Example class byte array (binary representation of a PNG file)
    encoded_class_data = base64.b64encode(class_data).decode('utf-8')
    
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as sock:
        sock.connect((host, port))
        sock.sendall(encoded_class_data.encode())
        
if __name__ == "__main__":
    send_class()