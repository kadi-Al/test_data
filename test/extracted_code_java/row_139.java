import xml.etree.ElementTree as ET

def generate_xml(name, age, city):
    # Create the root element
    root = ET.Element("user")
    
    # Add child elements with user-provided values
    name_elem = ET.SubElement(root, "name")
    name_elem.text = name
    
    age_elem = ET.SubElement(root, "age")
    age_elem.text = str(age)
    
    city_elem = ET.SubElement(root, "city")
    city_elem.text = city
    
    # Create an XML tree from the root element
    xml_tree = ET.ElementTree(root)
    
    # Convert the XML tree to a string
    xml_string = ET.tostring(root, encoding='utf-8').decode('utf-8')
    
    return xml_string

# Example usage
name = "John Doe"
age = 30
city = "New York"
xml_output = generate_xml(name, age, city)
print(xml_output)
xml
<user>
    <name>John Doe</name>
    <age>30</age>
    <city>New York</city>
</user>