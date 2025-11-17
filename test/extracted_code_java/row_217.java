import java.io.*;
import javax.servlet.http.HttpServletRequest;
import java.net.HttpURLConnection;

public class ObjectDeserializer {
    public static <T> T deserialize(byte[] bytes, Class<T> clazz) throws IOException, ClassNotFoundException {
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
             ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream)) {
            return clazz.cast(objectInputStream.readObject());
        }
    }
}
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deserialize")
public class DeserializeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Read the HTTP body into a byte array
        InputStream inputStream = request.getInputStream();
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[16384];
        while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        byte[] bytes = buffer.toByteArray();

        // Deserialize the byte array to your application class
        try {
            MyClass deserializedObject = ObjectDeserializer.deserialize(bytes, MyClass.class);
            // Use the deserialized object as needed
            response.getWriter().println("Deserialized: " + deserializedObject.toString());
        } catch (ClassNotFoundException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println("Error: Class not found during deserialization.");
        }
    }
}
public class MyClass implements Serializable {
    private static final long serialVersionUID = 1L;
    private String data;

    public MyClass(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "MyClass{data='" + data + "'}";
    }
}