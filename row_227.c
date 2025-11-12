sh
sudo apt-get install libmicrohttpd-dev
#include <microhttpd.h>
#include <stdio.h>
#include <string.h>

const char *answer_to_connection(void *cls, enum MHD_ValueKind kind, const char *key, const char *value) {
    if (strcmp(key, "echo") == 0 && value != NULL) {
        // Allocate memory for the response string
        char *response = malloc(256);
        snprintf(response, 256, "<p>%s</p>", value);
        return response;
    }
    return MHD_YES;
}

int request_handler(void *cls, struct MHD_Connection *connection, const char *url, const char *method, const char *version, const char *upload_data, size_t *upload_data_size, void **con_cls) {
    (void) cls;
    (void) url;
    (void) method;
    (void) version;
    (void) upload_data;
    (void) upload_data_size;
    (void) con_cls;

    enum MHD_Result result = MHD_YES;
    result = MHD_queue_response(connection, MHD_HTTP_OK, answer_to_connection);
    return result;
}

int main() {
    struct MHD_Daemon *daemon;

    daemon = MHD_start_daemon(MHD_USE_SELECT_INTERNALLY, 8080, NULL, NULL, &request_handler, NULL, MHD_OPTION_END);
    if (NULL == daemon) {
        fprintf(stderr, "Failed to start the daemon!\n");
        return 1;
    }

    printf("Server started on port 8080\n");
    getchar(); // Wait for Enter key press to stop the server

    MHD_stop_daemon(daemon);
    return 0;
}
sh
gcc -o cwebapp main.c -lmicrohttpd
sh
./cwebapp