#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <curl/curl.h>

// Function to handle incoming requests and check headers for access control
int handle_request(const char* request) {
    CURL *curl;
    CURLcode res;
    struct curl_slist *headers = NULL;

    // Add user-defined header to the request
    char custom_header[256];
    snprintf(custom_header, sizeof(custom_header), "X-Custom-Header: %s", request);
    headers = curl_slist_append(headers, custom_header);

    // Initialize curl session
    curl = curl_easy_init();
    if(curl) {
        // Set the URL to send the request to
        curl_easy_setopt(curl, CURLOPT_URL, "http://example.com");

        // Add headers
        curl_easy_setopt(curl, CURLOPT_HTTPHEADER, headers);

        // Perform the request
        res = curl_easy_perform(curl);

        // Cleanup
        curl_easy_cleanup(curl);
        curl_slist_free_all(headers);

        if (res != CURLE_OK) {
            fprintf(stderr, "curl_easy_perform() failed: %s\n", curl_easy_strerror(res));
            return -1;
        } else {
            // Check the response for access control logic
            long http_code = 0;
            curl_easy_getinfo(curl, CURLINFO_RESPONSE_CODE, &http_code);
            if (http_code == 200) {
                return 0; // Access granted
            } else {
                return -1; // Access denied
            }
        }
    }
    return -1;
}

int main(void) {
    char request[256];
    printf("Enter access control header: ");
    if (fgets(request, sizeof(request), stdin) != NULL) {
        // Remove newline character from the input
        size_t len = strlen(request);
        if (len > 0 && request[len - 1] == '\n') {
            request[len - 1] = '\0';
        }

        // Handle the request and check for access control
        int result = handle_request(request);
        if (result == 0) {
            printf("Access granted!\n");
        } else {
            printf("Access denied.\n");
        }
    }
    return 0;
}
sh
gcc -o access_control_server server.c -lcurl