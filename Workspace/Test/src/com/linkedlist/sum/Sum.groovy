package com.linkedlist.sum

import com.linkedlist.Node
import com.sort.MergeSort

import groovy.transform.CompileStatic

/**
 * @author Ameya
 * 
 * @see <a href="https://leetcode.com/problems/add-two-numbers/">Problem</a>
 *
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 */

@CompileStatic
class Sum {

  Sum(Node head1, Node head2) {
    this.head1 = head1
    this.head2 = head2
  }

  Node head1
  Node head2

  Node sum() {
    Node l1 = head1
    Node l2 = head2
    if (l1 == null || l2 == null) {
      return null
    }
    Node result = null
    Node current = null
    Node previous = null
    int carry = 0
    while (l1 != null || l2 != null) {
      int v1 = l1 != null ? Integer.parseInt(l1.data) : 0
      int v2 = l2 != null ? Integer.parseInt(l2.data) : 0
      int sum = v1 + v2 + carry
      carry = sum / 10 as int
      int val = ((l1 != null && l1.next != null)
          || (l2 != null && l2.next != null)) ? sum % 10 : sum
      current = new Node(val as String)
      if (previous == null) {
        result = current
      }
      else {
        previous.next = current
      }
      previous = current
      if (l1 != null) {
        l1 = l1.next
      }
      if (l2 != null) {
        l2 = l2.next
      }
    }
    return result
  }

  static void main(String[] args) {
    Node n11 = new Node('2')
    Node n12 = new Node('4')
    Node n13 = new Node('3')
    Node head1 = Node.buildList([n11, n12, n13])

    Node n21 = new Node('5')
    Node n22 = new Node('6')
    Node n23 = new Node('4')
    Node head2 = Node.buildList([n21, n22, n23])

    Sum sum1 = new Sum(head1, head2)
    print('Number 1: ')
    Node.printList(head1)
    print('Number 2: ')
    Node.printList(head2)
    Node summed1 = sum1.sum()
    print('     Sum: ')
    Node.printList(summed1)
    println('============================')
    Node n31 = new Node('2')
    Node n32 = new Node('4')
    Node head3 = Node.buildList([n31, n32])
    Sum sum2 = new Sum(head3, head2)
    print('Number 1: ')
    Node.printList(head3)
    print('Number 2: ')
    Node.printList(head2)
    Node summed2 = sum2.sum()
    print('     Sum: ')
    Node.printList(summed2)
  }
}

// Output:
//
// Number 1: 2 -> 4 -> 3 -> END
// Number 2: 5 -> 6 -> 4 -> END
//      Sum: 7 -> 0 -> 8 -> END
// ============================
// Number 1: 2 -> 4 -> END
// Number 2: 5 -> 6 -> 4 -> END
//      Sum: 7 -> 0 -> 5 -> END