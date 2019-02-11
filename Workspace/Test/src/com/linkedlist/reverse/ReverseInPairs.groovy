package com.linkedlist.reverse

import com.linkedlist.Node

import groovy.transform.CompileStatic

/**
 * @author Ameya
 * This program reverses a singly linked list in pairs
 */

@CompileStatic
class ReverseInPairs {

  ReverseInPairs(Node head) {
    this.head = head
  }
  
  Node head
  
  void reverseInPairsRecursive() {
    head = reverseInPairsRecursiveHelper(head)
  }
  
  Node reverseInPairsRecursiveHelper(Node node) {
    if (node == null || node.next == null) {
      return node
    }
    Node next = node.next
    Node rest = node.next.next
    node.next.next = node
    node.next = reverseInPairsRecursiveHelper(rest)
    return next
  }
  
  void reverseInPairsIterative() {
    if (head == null || head.next == null) { // size = 0 or 1
      return
    }
    Node current = head
    Node previous = null
    head = head.next
    while (current != null && current.next != null) {
      Node temp = current.next.next
      current.next.next = current
      if (previous != null) {
        previous.next = current.next
      }
      current.next = temp
      previous = current
      current = temp
    }
  }
  
  static void main (String[] args) {
    Node n1 = Node.buildList([new Node('A1'), new Node('B1'),
      new Node('C1'), new Node('A2'), new Node('B2'), new Node('C2')])
    println('Recursive')
    ReverseInPairs reverse1 = new ReverseInPairs(n1)
    println('===========')
    print('Before: ')
    Node.printList(reverse1.head)
    print('After: ')
    reverse1.reverseInPairsRecursive()
    Node.printList(reverse1.head)
    println('\nIterative')
    ReverseInPairs reverse2 = new ReverseInPairs(reverse1.head)
    println('=========')
    print('Before: ')
    Node.printList(reverse2.head)
    print('After: ')
    reverse2.reverseInPairsIterative()
    Node.printList(reverse2.head)
  }
}


// Output:
//
// Recursive
// ===========
// Before: A1 -> B1 -> C1 -> A2 -> B2 -> C2 -> END
// After: B1 -> A1 -> A2 -> C1 -> C2 -> B2 -> END
//
// Iterative
// =========
// Before: B1 -> A1 -> A2 -> C1 -> C2 -> B2 -> END
// After: A1 -> B1 -> C1 -> A2 -> B2 -> C2 -> END
