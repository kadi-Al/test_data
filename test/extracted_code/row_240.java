import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class XMLParser {
    public static void main(String[] args) {
        try {
            // Create a DocumentBuilderFactory instance
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            
            // Configure the factory to enable external entity resolution
            factory.setFeature("http://xml.org/sax/features/external-general-entities", true);
            factory.setFeature("http://xml.org/sax/features/external-parameter-entities", true);
            
            // Create a DocumentBuilder instance
            DocumentBuilder builder = factory.newDocumentBuilder();
            
            // Parse the XML document (replace with your own XML content)
            String xmlContent = "<root><element>Hello, World!</element></root>";
            Document doc = builder.parse(new java.io.ByteArrayInputStream(xmlContent.getBytes()));
            
            // Print the parsed XML for verification
            System.out.println("Parsed XML: " + doc.getDocumentElement().getTextContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}