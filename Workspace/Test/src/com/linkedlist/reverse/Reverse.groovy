package com.linkedlist.reverse

/**
 * @author Ameya
 * This program reverses a singly linked list using both iterative and recursive approaches
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

class Reverse {

    static Node reverseIterative(Node head) {
        if (head == null) {
            return null
        }
        Node previous = null
        Node current = head
        while (current != null) {
            Node temp = current.next
            current.next = previous
            previous = current
            current = temp
        }
        head = previous
        return head
    }

    static Node reverseRecursive(Node head) {
        if (head == null) {
            return null
        }
        return reverseRecursive(head)
    }

    static Node reverseRecursiveHelper(Node node) {
        if (node.next == null) {
            return node
        }
        return reverseRecursiveHelper(node.next).next = node
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

class ReverseTester {
    static void main (String[] args) {
        Node n1 = Reverse.createLinkedList(['A1', 'B1', 'C1', 'A2', 'B2', 'C2'])
        println('Iterational')
        println('===========')
        print('Before: ')
        Reverse.printList(n1)
        print('After: ')
        n1 = Reverse.reverseIterative(n1)
        Reverse.printList(n1)
        println('\nRecursive')
        println('=========')
        print('Before: ')
        Reverse.printList(n1)
        print('After: ')
        n1 = Reverse.reverseIterative(n1)
        Reverse.printList(n1)
    }
}

// Output:
//
// Iterational
// ===========
// Before: A1 -> B1 -> C1 -> A2 -> B2 -> C2 -> END
// After: C2 -> B2 -> A2 -> C1 -> B1 -> A1 -> END
//
// Recursive
// =========
// Before: C2 -> B2 -> A2 -> C1 -> B1 -> A1 -> END
// After: A1 -> B1 -> C1 -> A2 -> B2 -> C2 -> END
