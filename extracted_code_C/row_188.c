#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <curl/curl.h>

// Function to handle the data received from the server
size_t WriteCallback(void *contents, size_t size, size_t nmemb, void *userp) {
    ((char*)userp)[0] = '\0'; // Clear any previous data
    strcat((char*)userp, (char*)contents);
    return size * nmemb;
}

int main(void) {
    CURL *curl;
    CURLcode res;
    char url[256];
    char username[128];
    char password[128];
    char response_buffer[1024] = ""; // Buffer to store the response

    printf("Enter the URL: ");
    scanf("%s", url);
    printf("Enter the Username: ");
    scanf("%s", username);
    printf("Enter the Password: ");
    scanf("%s", password);

    curl_global_init(CURL_GLOBAL_DEFAULT);
    curl = curl_easy_init();

    if(curl) {
        // Set the URL for the request
        char *url_with_params = malloc(256 + 100); // Adjust sizes as necessary
        snprintf(url_with_params, 256 + 100, "%s?username=%s&password=%s", url, username, password);

        curl_easy_setopt(curl, CURLOPT_URL, url_with_params);

        // Set the callback function to handle the response
        curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, WriteCallback);
        curl_easy_setopt(curl, CURLOPT_WRITEDATA, response_buffer);

        // Perform the request and check for errors
        res = curl_easy_perform(curl);
        if(res != CURLE_OK) {
            fprintf(stderr, "curl_easy_perform() failed: %s\n", curl_easy_strerror(res));
        } else {
            // Print the response from the server
            printf("Server Response: %s\n", response_buffer);
        }

        // Cleanup
        free(url_with_params);
        curl_easy_cleanup(curl);
    }

    curl_global_cleanup();
    return 0;
}
bash
gcc -o http_client http_client.c -lcurl
./http_client