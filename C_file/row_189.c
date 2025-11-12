sh
   sudo apt-get install libxml2-dev
#include <stdio.h>
#include <stdlib.h>
#include <libxml/parser.h>
#include <libxml/tree.h>

void parse_xml(const char *filename) {
    xmlDocPtr doc;
    xmlNodePtr cur;

    // Enable ENTity Resolution (No External Entities)
    LIBXML_TEST_VERSION
    doc = xmlReadFile(filename, NULL, XML_PARSE_NOENT);
    if (doc == NULL) {
        fprintf(stderr, "Error: could not parse file %s\n", filename);
        return;
    }

    // Get the root element
    cur = xmlDocGetRootElement(doc);
    if (cur == NULL) {
        fprintf(stderr, "Error: Document is empty\n");
        xmlFreeDoc(doc);
        return;
    }

    // Print the XML structure
    print_element_names(cur, 0);

    // Free the document
    xmlFreeDoc(doc);
}

void print_element_names(xmlNodePtr node, int level) {
    xmlChar *name;

    for (int i = 0; i < level; ++i) {
        printf("  ");
    }

    if (node->type == XML_ELEMENT_NODE) {
        name = xmlGetProp(node, BAD_CAST "name");
        if (name != NULL) {
            printf("%s", name);
            xmlFree(name);
        } else {
            printf("Element: %s", node->name);
        }
    }

    printf("\n");

    // Recursively print child elements
    for (xmlNodePtr child = node->children; child != NULL; child = child->next) {
        print_element_names(child, level + 1);
    }
}

int main(int argc, char *argv[]) {
    if (argc <= 1) {
        fprintf(stderr, "Usage: %s <filename>\n", argv[0]);
        return EXIT_FAILURE;
    }

    parse_xml(argv[1]);
    xmlCleanupParser();
    return EXIT_SUCCESS;
}
sh
gcc -o xml_reader xml_reader.c -lxml2
sh
./xml_reader sample.xml