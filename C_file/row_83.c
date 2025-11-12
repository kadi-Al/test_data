int check_access(const char *file) {
    return access(file, W_OK);
}

FILE* open_file(const char *file, const char *mode) {
    return fopen(file, mode);
}

void safe_fopen(const char *file, const char *mode) {
    if (check_access(file) == 0) { // Check write access first
        FILE *fp = open_file(file, mode); // Then try to open the file
        if (fp != NULL) {
            // Use the file pointer fp safely...
            fclose(fp);
        } else {
            perror("fopen");
        }
    } else {
        fprintf(stderr, "File %s does not have write access.\n", file);
    }
}