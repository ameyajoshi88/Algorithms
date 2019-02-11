package com.linkedlist.intersection

import groovy.transform.CompileStatic
import com.linkedlist.Node

/**
 * @author Ameya
 * This program finds the node at which 2 linked lists intersect
 */

@CompileStatic
class Intersection {
  
  Intersection(Node head1, Node head2) {
    this.head1 = head1
    this.head2 = head2
  }
  
  Node head1
  Node head2
  
  Node intersection() {
    // Find lengths of lists
    int length1 = Node.length(head1)
    int length2 = Node.length(head2)
    int diff = length1 - length2
    if (diff > 0) {
      // list 1 > list 2
      intersectionHelper(head1, head2, diff)
    }
    else {
      // list 1 <= list 2
      intersectionHelper(head2, head1, -diff)
    }
  }
  
  Node intersectionHelper(Node longerListHead, Node shorterListHead, int diff) {
    Node longerListPointer = longerListHead
    Node shorterListPointer = shorterListHead
    // Advance longerListPointer by 'diff' steps
    for (int i = 0; i < diff; i++) {
      longerListPointer = longerListPointer.next
    }
    // Advance both pointers by 1 step and check for a match at each step
    while (longerListPointer != null) {
      if (longerListPointer == shorterListPointer) {
        return longerListPointer
      }
      longerListPointer = longerListPointer.next
      shorterListPointer = shorterListPointer.next
    }
  }
  
  static void main(String[] args) {
    Node l1n1 = new Node('L1N1')
    Node l1n2 = new Node('L1N2')
    Node l1n3 = new Node('L1N3-L2N2')
    Node l1n4 = new Node('L1N4-L2N3')
    Node l1n5 = new Node('L1N5-L2N4')
    Node head1 = Node.buildList([l1n1, l1n2, l1n3, l1n4, l1n5])
    
    Node l2n1 = new Node('L2N1')
    Node l2n2 = l1n3
    Node l2n3 = l1n4
    Node l2n4 = l1n5
    Node head2 = Node.buildList([l2n1, l2n2, l2n3, l2n4])
    
    Intersection intersection1 = new Intersection(head1, head2)
    assert l1n3 == intersection1.intersection()
    Intersection intersection2 = new Intersection(head2, head1)
    assert l1n3 == intersection2.intersection()
  }
}
