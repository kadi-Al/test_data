import com.thoughtworks.xstream.XStream;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

public class SafeXStreamExample {
    public static void main(String[] args) {
        // Create a trusted input XML string
        String xml = "<user><name>John Doe</name><age>30</age></user>";
        
        // Initialize XStream and set security policies if necessary
        XStream xstream = new XStream();
        xstream.allowTypes(new Class[] {User.class});
        xstream.addDefaultImplementation(java.util.HashMap.class, java.util.LinkedHashMap.class);
        
        // Deserialize the XML into an object
        User user = (User) xstream.fromXML(xml);
        
        // Print out the deserialized object
        System.out.println("Name: " + user.getName());
        System.out.println("Age: " + user.getAge());
    }
}

class User {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
import com.thoughtworks.xstream.XStream;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

public class SafeXStreamExample {
    public static void main(String[] args) {
        // Assume userInput is sanitized and validated before use
        String userInput = "<user><name>John Doe</name><age>30</age></user>";
        
        // Initialize XStream with security policies
        XStream xstream = new XStream();
        xstream.allowTypes(new Class[] {User.class});
        xstream.addDefaultImplementation(java.util.HashMap.class, java.util.LinkedHashMap.class);
        
        // Deserialize the XML into an object
        User user = (User) xstream.fromXML(userInput);
        
        // Print out the deserialized object
        System.out.println("Name: " + user.getName());
        System.out.println("Age: " + user.getAge());
    }
}

class User {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}