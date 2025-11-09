import org.apache.xalan.dom.NodeBuilder;
import org.apache.xalan.processor.TransformerFactoryImpl;
import org.w3c.dom.Document;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import java.io.FileInputStream;
import java.net.URL;

public class XSLTProcessor {
    public static void main(String[] args) throws Exception {
        // Load the XML document
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        FileInputStream xmlInput = new FileInputStream("input.xml");
        org.w3c.dom.Document xmlDoc = factory.newDocumentBuilder().parse(xmlInput);

        // Define the XSLT stylesheet URL (can be user-supplied)
        String xsltUrlString = "http://example.com/styles.xsl";
        URL xsltUrl = new URL(xsltUrlString);
        FileInputStream xsltInput = new FileInputStream("localStyles.xsl"); // Fallback to local XSLT

        // Load the stylesheet from either remote or local source
        Source xsltSource;
        if (xsltUrl != null) {
            xsltSource = new DOMSource(loadRemoteXSLT(xsltUrl));
        } else {
            xsltSource = new StreamSource(xsltInput);
        }

        // Create a transformer factory and use it to create a transformer
        TransformerFactory tFactory = new TransformerFactoryImpl();
        Transformer transformer = tFactory.newTransformer(xsltSource);

        // Transform the XML document using the XSLT stylesheet
        DOMResult result = new DOMResult();
        transformer.transform(new DOMSource(xmlDoc), result);

        // Output the transformed document (for example, print it to console)
        org.w3c.dom.Document transformedDoc = result.getNode().getOwnerDocument();
        System.out.println(transformedDoc.toString());
    }

    private static Document loadRemoteXSLT(URL url) throws Exception {
        // Load the XSLT from the remote URL and return a DOM document
        Source src = new StreamSource(url.openStream());
        NodeBuilder builder = new NodeBuilder();
        org.w3c.dom.Document xsltDoc = builder.build(src);
        return xsltDoc;
    }
}