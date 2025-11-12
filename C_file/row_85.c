import xml.etree.ElementTree as ET

# Define the XML content with an external entity
xml_data = '''<?xml version="1.0"?>
<root>
    <element>
        <!-- This is a simple XML example with an external entity -->
        <!ENTITY xxe "file:///etc/passwd">
    </element>
</root>'''

# Parse the XML data
try:
    tree = ET.fromstring(xml_data)
    print("XML parsed successfully!")
    # Accessing element data (for demonstration purposes, we will try to access an entity)
    if tree.find('.//element').text is not None:
        text = tree.find('.//element').text
        print("Element text:", text)
    else:
        print("No such element found!")
except ET.ParseError as e:
    print("XML Parsing Error:", e)