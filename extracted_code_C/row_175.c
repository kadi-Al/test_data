#include <stdio.h>
#include <stdlib.h>

// Define the structure for a node in the linked list
typedef struct Node {
    int data;
    struct Node* next;
} Node;

// Function to remove a node from the list after freeing it
void remove_node(Node** head, int key) {
    // If the list is empty or the key is not found, return immediately
    if (*head == NULL) {
        return;
    }

    Node* temp = *head;
    Node* prev = NULL;

    // Traverse the list to find the node with the given key
    while (temp != NULL && temp->data != key) {
        prev = temp;
        temp = temp->next;
    }

    // If the key is not found, return immediately
    if (temp == NULL) {
        return;
    }

    // Unlink the node from the list and free it
    if (prev != NULL) {
        prev->next = temp->next;
    } else {
        *head = temp->next;
    }

    free(temp);
}

// Function to add a new node to the list
void push(Node** head, int data) {
    Node* new_node = (Node*)malloc(sizeof(Node));
    new_node->data = data;
    new_node->next = (*head);
    (*head) = new_node;
}

// Function to print the list
void printList(Node* head) {
    Node* temp = head;
    while (temp != NULL) {
        printf("%d -> ", temp->data);
        temp = temp->next;
    }
    printf("NULL\n");
}

// Main function to demonstrate the list removal functionality
int main() {
    Node* head = NULL;

    // Add nodes to the list
    push(&head, 1);
    push(&head, 2);
    push(&head, 3);
    push(&head, 4);

    printf("Linked list before removal:\n");
    printList(head);

    // Remove a node with value 3
    remove_node(&head, 3);

    printf("Linked list after removal:\n");
    printList(head);

    return 0;
}