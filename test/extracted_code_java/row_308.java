import java.io.FileOutputStream;
import java.io.IOException;

public class GetentPasswd {
    public static void main(String[] args) {
        String username = "username"; // Replace with the actual username
        try {
            Process process = Runtime.getRuntime().exec("getent passwd " + username);
            FileOutputStream fileOut = new FileOutputStream("output");
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = process.getInputStream().read(buffer)) != -1) {
                fileOut.write(buffer, 0, bytesRead);
            }
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
import java.io.FileOutputStream;
import java.io.IOException;

public class GetentPasswd {
    public static void main(String[] args) {
        String username = "username"; // Replace with the actual username
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("getent", "passwd", username);
            Process process = processBuilder.start();
            FileOutputStream fileOut = new FileOutputStream("output");
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = process.getInputStream().read(buffer)) != -1) {
                fileOut.write(buffer, 0, bytesRead);
            }
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class GetentPasswd {
    public static void main(String[] args) {
        String username = "username"; // Replace with the actual username
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("getent passwd " + username).getInputStream()));
             FileOutputStream fileOut = new FileOutputStream("output")) {
            char[] buffer = new char[1024];
            int charsRead;
            while ((charsRead = reader.read(buffer)) != -1) {
                fileOut.write(buffer, 0, charsRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}