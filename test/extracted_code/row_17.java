class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class CircularLinkedList {
    private Node head;
    private Node tail;
    private int capacity;
    private int size;

    public CircularLinkedList(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.head = null;
        this.tail = null;
    }

    // Insert at head of the list
    public void insertAtHead(int data) {
        if (isFull()) {
            System.out.println("Overflow: Cannot insert, list is full.");
            return;
        }
        Node newNode = new Node(data);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
            newNode.next = head;
        } else {
            newNode.next = head;
            head = newNode;
            tail.next = head; // Update the last node to point to the new head
        }
        size++;
    }

    // Insert at tail of the list
    public void insertAtTail(int data) {
        if (isFull()) {
            System.out.println("Overflow: Cannot insert, list is full.");
            return;
        }
        Node newNode = new Node(data);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
            newNode.next = head;
        } else {
            tail.next = newNode;
            tail = newNode;
            tail.next = head; // Update the last node to point to the head
        }
        size++;
    }

    // Delete from head of the list
    public void deleteAtHead() {
        if (isEmpty()) {
            System.out.println("Underflow: Cannot delete, list is empty.");
            return;
        }
        if (head == tail) { // Only one element in the list
            head = null;
            tail = null;
        } else {
            head = head.next;
            tail.next = head; // Update the last node to point to the new head
        }
        size--;
    }

    // Check if the list is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Check if the list is full
    public boolean isFull() {
        return size == capacity;
    }

    // Expand the capacity of the list
    public void expandCapacity(int newCapacity) {
        this.capacity = newCapacity;
        System.out.println("Capacity expanded to: " + capacity);
    }

    // Display the elements in the circular linked list
    public void display() {
        if (isEmpty()) {
            System.out.println("List is empty.");
            return;
        }
        Node current = head;
        do {
            System.out.print(current.data + " ");
            current = current.next;
        } while (current != head);
        System.out.println();
    }

    public static void main(String[] args) {
        CircularLinkedList cll = new CircularLinkedList(3);
        cll.insertAtHead(1);
        cll.insertAtTail(2);
        cll.insertAtTail(3);
        cll.display(); // Output: 1 2 3

        cll.insertAtTail(4); // Should show overflow message
        cll.deleteAtHead();
        cll.deleteAtHead();
        cll.deleteAtHead();
        cll.deleteAtHead(); // Should show underflow message

        cll.expandCapacity(5);
        cll.insertAtTail(4);
        cll.insertAtTail(5);
        cll.display(); // Output: 4 5
    }
}