#include <stdio.h>
#include <stdlib.h>
#include <libxml/parser.h>
#include <libxml/tree.h>
#include <string.h>

// Function to securely read an XML file using libxml2 with NOENT flag
void secure_read_xml(const char* xml_file) {
    // Initialize the parser context
    xmlParserCtxtPtr ctx = xmlNewParserCtxt();
    if (ctx == NULL) {
        fprintf(stderr, "Failed to allocate parser context\n");
        return;
    }

    // Set XML_PARSE_NOENT flag to expand entity references
    ctx->loadsubset = 1;
    ctx->options |= XML_PARSE_NOENT;

    // Load the XML document from file
    xmlDocPtr doc = xmlCtxtReadFile(ctx, xml_file, NULL, XML_PARSE_NOENT);
    if (doc == NULL) {
        fprintf(stderr, "Failed to parse %s\n", xml_file);
        xmlFreeParserCtxt(ctx);
        return;
    }

    // Print the document name for verification
    printf("Parsed XML file: %s\n", xml_file);

    // Clean up resources
    xmlFreeDoc(doc);
    xmlFreeParserCtxt(ctx);
}

// Main function to demonstrate usage of secure_read_xml
int main(int argc, char* argv[]) {
    if (argc < 2) {
        fprintf(stderr, "Usage: %s <XML file>\n", argv[0]);
        return EXIT_FAILURE;
    }

    // Initialize the libxml2 library
    xmlInitParser();

    // Call the function to read and parse the XML file
    secure_read_xml(argv[1]);

    // Cleanup the libxml2 library
    xmlCleanupParser();

    return EXIT_SUCCESS;
}
bash
gcc -o secure_xml_reader secure_xml_reader.c -lxml2
./secure_xml_reader example.xml