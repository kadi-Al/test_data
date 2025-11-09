xml
   <dependency>
       <groupId>javax.xml.bind</groupId>
       <artifactId>jaxb-api</artifactId>
       <version>2.3.1</version>
   </dependency>
   <dependency>
       <groupId>org.glassfish.jaxb</groupId>
       <artifactId>jaxb-runtime</artifactId>
       <version>2.3.1</version>
   </dependency>
import javax.xml.bind.annotation.*;

   @XmlRootElement(name = "root")
   @XmlAccessorType(XmlAccessType.FIELD)
   public class Root {
       @XmlElement(name = "element")
       private String content;

       // Getters and setters
       public String getContent() { return content; }
       public void setContent(String content) { this.content = content; }
   }
import javax.xml.bind.*;
   import java.io.StringReader;

   public class UnmarshallerExample {
       public static void main(String[] args) {
           String xmlData = "<root><element>Some user input</element></root>";
           try (StringReader reader = new StringReader(xmlData)) {
               JAXBContext jaxbContext = JAXBContext.newInstance(Root.class);
               Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
               Root rootElement = (Root) unmarshaller.unmarshal(reader);
               System.out.println("Content: " + rootElement.getContent());
           } catch (JAXBException e) {
               e.printStackTrace();
           }
       }
   }
JAXBContext jaxbContext = JAXBContext.newInstance(Root.class);
     Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
     // Disable external entities
     ((javax.xml.bind.helpers.DefaultUnmarshallingHandler)unmarshaller.getListener()).setProcessExternalEntities(false);
     Root rootElement = (Root) unmarshaller.unmarshal(reader);