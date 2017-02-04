package com.linkedlist.weave

/**
 * @author Ameya
 * This program creates takes a singly linked list of even number of elements of the form: A1, A2,...An, B1, B2,...Bn
 * and converts it into a weaved linked list of the form A1, A2, B1, B2, ...An, Bn
 */
class Node {

    Node(String data) {
        this.data = data
    }

    String data
    Node next

    String toString() {
        return this.data
    }
}

class Weave {

    static void weave(Node head) {
        if (head == null) {
            return
        }
        // Assume list is having even number of elements
        Node l1 = head
        Node l2 = head
        while (l2 != null) {
            l1 = l1.next
            l2 = l2.next.next
        }
        // Now l1 is the head of sublist2, so assign it to l2 and reset l1
        l2 = l1
        l1 = head
        // Start weaving your magic!
        Node nextL1, nextL2
        while (l2 != null) {
            nextL1 = l1.next
            l1.next = l2
            nextL2 = l2.next
            if (nextL2 != null) {
                l2.next = nextL1 // To terminate the list
            }
            l2 = nextL2
            l1 = nextL1
        }
    }

    static void printList(Node head) {
        Node node = head
        while (node != null) {
            print("${node} -> ")
            node = node.next
        }
        println('END')
    }

    static Node createLinkedList(List<String> data) {
        if(data == null || data.isEmpty()) {
            return null
        }
        Node head = new Node(data[0])
        Node previous = head
        for (int i=1; i<data.size(); i++) {
            Node current = new Node(data[i])
            previous.next = current
            previous = current
        }
        return head
    }
}

class WeaveTester {
    static void main(String[] args) {
        Node n1 = Weave.createLinkedList(['A1', 'B1', 'C1', 'A2', 'B2', 'C2'])
        print('Before: ')
        Weave.printList(n1)
        print('After: ')
        Weave.weave(n1)
        Weave.printList(n1)
    }
}

// Output:
//
// Before: A1 -> B1 -> C1 -> A2 -> B2 -> C2 -> END
// After: A1 -> A2 -> B1 -> B2 -> C1 -> C2 -> END
