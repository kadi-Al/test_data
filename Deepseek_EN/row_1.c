#include <stdio.h>
#include <stdlib.h>
#include <libxml/parser.h>
#include <libxml/parser_internal.h>
#include <libxml/tree.h>

#define MAX_XML_SIZE 1024 * 64 // 64 KB limit for XML input size

void handle_error(const char* msg) {
    fprintf(stderr, "Error: %s\n", msg);
}

int validate_xml_size(const char* xml_data, size_t len) {
    if (len > MAX_XML_SIZE) {
        handle_error("XML document exceeds the allowed maximum size.");
        return 0;
    }
    return 1;
}

int main(int argc, char** argv) {
    if (argc < 2) {
        fprintf(stderr, "Usage: %s <file.xml>\n", argv[0]);
        return EXIT_FAILURE;
    }

    xmlInitParser();
    LIBXML_TEST_VERSION // Ensure libxml version matches the header used.

    FILE* fp = fopen(argv[1], "r");
    if (!fp) {
        handle_error("Failed to open XML file.");
        return EXIT_FAILURE;
    }

    fseek(fp, 0L, SEEK_END);
    long xml_size = ftell(fp);
    rewind(fp);

    char* xml_data = (char*)malloc(xml_size + 1);
    if (!xml_data) {
        handle_error("Failed to allocate memory for XML data.");
        fclose(fp);
        return EXIT_FAILURE;
    }

    size_t bytes_read = fread(xml_data, 1, xml_size, fp);
    if (bytes_read != xml_size) {
        handle_error("Failed to read XML data.");
        free(xml_data);
        fclose(fp);
        return EXIT_FAILURE;
    }
    xml_data[xml_size] = '\0'; // Null-terminate the string

    if (!validate_xml_size(xml_data, xml_size)) {
        free(xml_data);
        fclose(fp);
        return EXIT_FAILURE;
    }

    // Parse the XML data with NOENT to handle entities properly
    xmlDocPtr doc = xmlParseMemory(xml_data, xml_size);
    if (!doc) {
        handle_error("Failed to parse XML document.");
        free(xml_data);
        fclose(fp);
        return EXIT_FAILURE;
    }

    // Process the parsed XML tree here (example: print the root element name)
    xmlNodePtr root = xmlDocGetRootElement(doc);
    if (!root) {
        handle_error("Empty or malformed XML document.");
        xmlFreeDoc(doc);
        free(xml_data);
        fclose(fp);
        return EXIT_FAILURE;
    }

    printf("Root element: %s\n", root->name);

    // Cleanup and release resources
    xmlFreeDoc(doc);
    free(xml_data);
    fclose(fp);
    xmlCleanupParser();
    return EXIT_SUCCESS;
}