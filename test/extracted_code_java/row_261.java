import javax.xml.xpath.*;
import org.w3c.dom.*;
import org.xml.sax.InputSource;
import java.io.StringReader;

public class XPathExample {
    public static void main(String[] args) throws Exception {
        // Sample XML content as a string
        String xmlContent = "<library><book id='123'>Java Programming</book><book id='456'>XML Basics</book></library>";
        
        // User input for XPath expression parts (assuming user inputs are safe)
        String userXPathExpressionPart = "//book[@id='";
        String userId = "123";
        String closingPart = "']/text()";
        
        // Assemble the full XPath expression from user input
        String xpathExpression = userXPathExpressionPart + userId + closingPart;
        
        // Compile the XPath expression
        XPathFactory xpathFactory = XPathFactory.newInstance();
        XPath xpath = xpathFactory.newXPath();
        XPathExpression expr = xpath.compile(xpathExpression);
        
        // Parse the XML content
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(xmlContent));
        Document doc = builder.parse(is);
        
        // Evaluate the XPath expression over the XML document
        Object result = expr.evaluate(doc, XPathConstants.NODESET);
        NodeList nodes = (NodeList) result;
        
        // Print the results
        for (int i = 0; i < nodes.getLength(); i++) {
            System.out.println(nodes.item(i).getNodeValue());
        }
    }
}