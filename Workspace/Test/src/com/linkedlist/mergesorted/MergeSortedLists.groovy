package com.linkedlist.mergesorted

/**
 * @author Ameya
 * This program merges 2 sorted linked lists
 * e.g.
 *
 * 1 -> 3 -> 5 ->
 * 2 -> 4 -> 6 ->
 *
 * Output: 1 -> 2 -> 3 -> 4 -> 5 -> 6 ->
 */
class MergeSortedLists {

    static class Node {
        int data
        Node next

        Node(int data) {
            this.data = data
        }
    }

    static Node merge(Node l1, Node l2) {
        if(l1 == null) {
            return l2
        }
        if(l2 == null) {
            return l1
        }
        Node head
        if(l1.data <= l2.data) {
            head = l1
            l1 = l1.next
        }
        else {
            head = l2
            l2 = l2.next
        }
        Node current = head
        while(l1 != null && l2 != null) {
            if(l1.data <= l2.data) {
                current.next = l1
                l1 = l1.next
            }
            else {
                current.next = l2
                l2 = l2.next
            }
            current = current.next
        }
        if(l1 == null) {
            current.next = l2
        }
        if(l2 == null) {
            current.next = l1
        }
        return head
    }

    static void display(Node list) {
        while (list != null) {
            print("$list.data -> ")
            list = list.next
        }
        println()
    }

    static void main(String[] args) {
        Node n11 = new Node(1)
        Node n12 = new Node(3)
        Node n13 = new Node(5)
        n11.next = n12
        n12.next = n13

        Node n21 = new Node(2)
        Node n22 = new Node(4)
        Node n23 = new Node(6)
        n21.next = n22
        n22.next = n23

        display(n11)
        display(n21)
        merge(n11, n21)
        display(n11)
    }
}
