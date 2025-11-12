#include <stdio.h>
#include <stdlib.h>
#include <libxml/parser.h>
#include <libxml/xmlerror.h>

void parse_xml(const char *data) {
    xmlDocPtr doc;
    xmlNodePtr root_element;

    // Disable external entities
    xmlSetExternalEntityLoader(NULL);

    // Parse the XML data
    doc = xmlReadMemory(data, strlen(data), "noname.xml", NULL, 0);
    if (doc == NULL) {
        fprintf(stderr, "Error parsing XML: %s\n", xmlGetLastError()->message);
        return;
    }

    // Get the root element
    root_element = xmlDocGetRootElement(doc);
    if (root_element == NULL) {
        fprintf(stderr, "Empty document.\n");
        xmlFreeDoc(doc);
        return;
    }

    // Print the root element name for demonstration purposes
    printf("Root element: %s\n", root_element->name);

    // Free the document and cleanup
    xmlFreeDoc(doc);
    xmlCleanupParser();
}

int main() {
    char buffer[1024];
    size_t bytesRead;

    printf("Enter XML data: ");
    while ((bytesRead = fread(buffer, 1, sizeof(buffer), stdin)) > 0) {
        parse_xml((const char *)buffer);
    }

    return 0;
}