import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class SafeSAXParser {
    public static void main(String[] args) {
        try {
            // Create a new SAXParserFactory instance with default properties
            SAXParserFactory factory = SAXParserFactory.newInstance();
            
            // Configure the parser to prevent XML external entities (XXE) attacks
            factory.setFeature("http://xml.org/sax/features/external-general-entities", false);
            factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            
            // Create a new SAXParser instance using the configured factory
            SAXParser parser = factory.newSAXParser();
            
            // Create an XMLReader from the SAXParser
            XMLReader reader = parser.getXMLReader();
            
            // Parse the input source (in this case, assume it's a string of XML)
            String xmlData = "<root><element>content</element></root>";
            InputSource is = new InputSource(new java.io.StringReader(xmlData));
            
            // Set the property to true to expand entity references (default behavior)
            reader.setEntityResolver((publicId, systemId) -> {
                return new org.xml.sax.InputSource(new java.io.ByteArrayInputStream("".getBytes()));
            });
            
            // Parse the XML data using the SAX parser
            reader.parse(is);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}