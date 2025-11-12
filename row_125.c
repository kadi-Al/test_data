#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define CONFIG_FILE "config.txt"
#define API_KEY_MAX_LENGTH 100

void saveApiKey(const char* apiKey) {
    FILE* file = fopen(CONFIG_FILE, "w");
    if (file == NULL) {
        perror("Failed to open config file");
        return;
    }

    if (fprintf(file, "%s\n", apiKey) < 0) {
        perror("Failed to write to config file");
        fclose(file);
        return;
    }

    fclose(file);
}

char* loadApiKey() {
    FILE* file = fopen(CONFIG_FILE, "r");
    if (file == NULL) {
        perror("Failed to open config file");
        return NULL;
    }

    char* apiKey = (char*)malloc(API_KEY_MAX_LENGTH);
    if (apiKey == NULL) {
        fclose(file);
        perror("Failed to allocate memory for API key");
        return NULL;
    }

    if (fgets(apiKey, API_KEY_MAX_LENGTH, file) == NULL) {
        free(apiKey);
        fclose(file);
        return NULL;
    }

    // Remove newline character if present
    apiKey[strcspn(apiKey, "\n")] = '\0';

    fclose(file);
    return apiKey;
}

int main() {
    const char* newApiKey = "your_api_key_here";
    saveApiKey(newApiKey);

    char* loadedApiKey = loadApiKey();
    if (loadedApiKey != NULL) {
        printf("Loaded API Key: %s\n", loadedApiKey);
        free(loadedApiKey);
    } else {
        printf("Failed to load API key.\n");
    }

    return 0;
}