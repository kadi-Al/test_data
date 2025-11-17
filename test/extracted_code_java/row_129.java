import javax.management.*;
import javax.management.remote.*;
import java.lang.management.*;
import java.util.*;

public class JmxDynamicInvocation {
    public static void main(String[] args) throws Exception {
        // Assuming user input for the operation name
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the operation name: ");
        String operationName = scanner.nextLine();

        // Constructing a JMX client connection
        JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:1099/jmxrmi");
        InitialContext ctx = new InitialContext();
        MBeanServerConnection connection = javax.management.remote.rmi.RMIConnectorServer.newClient(url).getMBeanServerConnection();

        // Constructing the ObjectName for the MBean
        String mbeanName = "java.lang:type=OperatingSystem"; // Example, can be user input or dynamically constructed
        ObjectName objectName = new ObjectName(mbeanName);

        // Invoking the operation dynamically
        MBeanOperationInfo operationInfo = (MBeanOperationInfo) connection.getMBeanInfo(objectName).getOperations().get(0);
        if (operationInfo != null && operationInfo.getName().equals(operationName)) {
            System.out.println("Invoking operation: " + operationName);
            Object[] params = {}; // Define parameters if needed
            String[] signature = {}; // Define parameter types if needed
            connection.invoke(objectName, operationInfo.getName(), params, signature);
        } else {
            System.out.println("Operation not found: " + operationName);
        }
    }
}
import javax.management.*;
import javax.management.remote.*;
import java.lang.management.*;
import java.util.*;

public class JmxDynamicInvocation {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the operation name: ");
        String operationName = scanner.nextLine();

        // Basic validation to prevent common attacks
        if (operationName == null || operationName.trim().isEmpty()) {
            System.out.println("Invalid operation name.");
            return;
        }

        JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:1099/jmxrmi");
        InitialContext ctx = new InitialContext();
        MBeanServerConnection connection = javax.management.remote.rmi.RMIConnectorServer.newClient(url).getMBeanServerConnection();

        String mbeanName = "java.lang:type=OperatingSystem"; // Example, can be user input or dynamically constructed
        ObjectName objectName = new ObjectName(mbeanName);

        MBeanOperationInfo operationInfo = Arrays.stream(connection.getMBeanInfo(objectName).getOperations())
                .filter(op -> op.getName().equals(operationName))
                .findFirst()
                .orElse(null);

        if (operationInfo != null) {
            System.out.println("Invoking operation: " + operationName);
            Object[] params = {}; // Define parameters if needed
            String[] signature = {}; // Define parameter types if needed
            connection.invoke(objectName, operationInfo.getName(), params, signature);
        } else {
            System.out.println("Operation not found: " + operationName);
        }
    }
}