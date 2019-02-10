package com.linkedlist.reverse

import com.linkedlist.Node

import groovy.transform.CompileStatic

/**
 * @author Ameya
 * This program reverses a singly linked list using both iterative 
 * and recursive approaches
 */

@CompileStatic
class Reverse {

  Reverse(Node head) {
    this.head = head
  }
  
  Node head
  
  void reverseIterative() {
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
  }

  void reverseRecursive() {
    if (head == null) {
      return
    }
    Node node = reverseRecursiveHelper(head)
    node.next = null
  }
  
  Node reverseRecursiveHelper(Node node) {
    if (node.next == null) {
      head = node
      return node
    }
    reverseRecursiveHelper(node.next).next = node
    return node
  }
  
  static void main (String[] args) {
    Node n1 = Node.buildList([new Node('A1'), new Node('B1'),
      new Node('C1'), new Node('A2'), new Node('B2'), new Node('C2')])
    println('Iterative')
    Reverse reverse1 = new Reverse(n1)
    println('===========')
    print('Before: ')
    Node.printList(reverse1.head)
    print('After: ')
    reverse1.reverseIterative()
    Node.printList(reverse1.head)
    println('\nRecursive')
    Reverse reverse2 = new Reverse(reverse1.head)
    println('=========')
    print('Before: ')
    Node.printList(reverse2.head)
    print('After: ')
    reverse2.reverseRecursive()
    Node.printList(reverse2.head)
  }
}


// Output:
//
// Iterative
// ===========
// Before: A1 -> B1 -> C1 -> A2 -> B2 -> C2 -> END
// After: C2 -> B2 -> A2 -> C1 -> B1 -> A1 -> END
//
// Recursive
// =========
// Before: C2 -> B2 -> A2 -> C1 -> B1 -> A1 -> END
// After: A1 -> B1 -> C1 -> A2 -> B2 -> C2 -> END
