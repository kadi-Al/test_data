sh
sudo apt-get install libxml2-dev
#include <stdio.h>
#include <stdlib.h>
#include <libxml/parser.h>
#include <libxml/tree.h>

// Function to handle external entity (for security)
static int externalEntityLoader(void *ctx, const char *name, const char *type, const char *systemID, const char *publicID) {
    // Prevent loading of external entities
    return 1;
}

int main(int argc, char **argv) {
    if (argc != 2) {
        fprintf(stderr, "Usage: %s <filename>\n", argv[0]);
        return EXIT_FAILURE;
    }

    // Initialize the XML library and parser
    xmlInitParser();
    libxmlRegisterExternalEntityLoader(NULL, externalEntityLoader);

    // Parse the file
    xmlDocPtr doc = NULL;
    xmlNodePtr cur = NULL;

    doc = xmlParseFile(argv[1]);
    if (doc == NULL) {
        fprintf(stderr, "Failed to parse %s\n", argv[1]);
        xmlCleanupParser();
        return EXIT_FAILURE;
    }

    // Get the root element node
    cur = xmlDocGetRootElement(doc);
    if (cur == NULL) {
        fprintf(stderr, "Empty document\n");
        xmlFreeDoc(doc);
        xmlCleanupParser();
        return EXIT_FAILURE;
    }

    // Print the root element name
    printf("Root element: %s\n", cur->name);

    // Free the document and cleanup the parser
    xmlFreeDoc(doc);
    xmlCleanupParser();
    xmlMemoryDump();

    return EXIT_SUCCESS;
}
sh
gcc -o xml_parser xml_parser.c -lxml2
sh
./xml_parser example.xml