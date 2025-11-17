#include <stdio.h>
#include <string.h>
#include <unistd.h> // For send() function

int main() {
    int socket_fd; // Assume this is initialized and connected to a server
    char credit_card[] = "1234-5678-9012-3456"; // Example credit card number
    int credit_card_length = strlen(credit_card);

    // Send the credit card data over the socket
    send(socket_fd, credit_card, credit_card_length, 0);

    return 0;
}