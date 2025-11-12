#include <stdio.h>
#include <stdlib.h>

char *get_api_key() {
    const char *env_var = getenv("API_KEY");
    if (env_var != NULL) {
        return strdup(env_var);
    } else {
        fprintf(stderr, "API key not set.\n");
        exit(EXIT_FAILURE);
    }
}

int main() {
    char *api_key = get_api_key();
    printf("API Key: %s\n", api_key);
    free(api_key); // Don't forget to free the allocated memory
    return 0;
}
sh
export API_KEY="AKIAIOSFODNN7EXAMPLE"
./your_program