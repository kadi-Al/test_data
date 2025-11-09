from lxml import etree
import urllib.parse

def fetch_user_input():
    return input("Please enter the search term: ")  # User input should be sanitized in real applications

def build_xpath_query(user_input):
    base_xpath = "/root/item"  # Example XPath expression; imagine this is part of a larger context
    encoded_input = urllib.parse.quote(user_input)  # Encoding the user input to prevent injection of characters that break the query
    full_xpath = f"{base_xpath}[contains(text(), '{encoded_input}')]"
    return full_xpath

# Main function
def main():
    user_input = fetch_user_input()
    xpath_query = build_xpath_query(user_input)
    print("Generated XPath Query:", xpath_query)

    # Assuming you have an XML document to query against
    xml_data = '''<?xml version="1.0"?>
    <root>
        <item>Apple</item>
        <item>Banana</item>
        <item>Cherry</item>
        <item>Date</item>
    </root>'''
    
    root = etree.fromstring(xml_data)
    results = root.xpath(xpath_query)
    
    if results:
        print("Found items:", [item.text for item in results])
    else:
        print("No matching items found.")

if __name__ == "__main__":
    main()