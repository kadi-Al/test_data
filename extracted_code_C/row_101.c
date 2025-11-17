#include <stdio.h>
#include <stdlib.h>

int main() {
    char user_input[100];
    printf("Enter some text: ");
    fgets(user_input, sizeof(user_input), stdin);

    // Buggy part: Incorrect format string
    sprintf(user_input, "You entered: %d", user_input);

    printf("%s", user_input);

    return 0;
}
#include <stdio.h>
#include <stdlib.h>

int main() {
    char user_input[100];
    printf("Enter some text: ");
    fgets(user_input, sizeof(user_input), stdin);

    // Correct usage of sprintf for strings
    sprintf(user_input, "You entered: %s", user_input);

    printf("%s", user_input);

    return 0;
}