import xml.etree.ElementTree as ET

# Sample XML without any external entity references
xml_data = '''<?xml version="1.0"?>
<root>
    <element>content</element>
</root>'''

try:
    root = ET.fromstring(xml_data)
    print(root.find('element').text)  # Output: content
except ET.ParseError as e:
    print("XML Parsing Error:", e)
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

public class XMLParser {
    public static void main(String[] args) {
        try {
            File xmlFile = new File("data.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            dbFactory.setFeature("http://xml.org/sax/features/external-general-entities", false);
            dbFactory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
php
$xml = simplexml_load_string($xmlString, "SimpleXMLElement", LIBXML_NOENT | LIBXML_DTDLOAD);
if ($xml === false) {
    die('Error while parsing the XML');
}
sharp
using System;
using System.IO;
using System.Xml;

class Program {
    static void Main() {
        string xmlData = "<root><element>content</element></root>";
        var settings = new XmlReaderSettings();
        settings.DtdProcessing = DtdProcessing.Ignore;
        using (var stream = new StringReader(xmlData))
        using (XmlReader reader = XmlReader.Create(stream, settings)) {
            while (reader.Read()) ;
        }
    }
}