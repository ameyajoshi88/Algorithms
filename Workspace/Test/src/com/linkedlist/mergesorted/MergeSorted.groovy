package com.linkedlist.mergesorted

import com.linkedlist.Node
import com.sort.MergeSort

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
class MergeSorted {

  MergeSorted(Node head1, Node head2) {
    this.head1 = head1
    this.head2 = head2
  }

  Node head1
  Node head2

  Node merge() {
    if (head1 == null) {
      return head2
    }
    if (head2 == null) {
      return head1
    }
    Node mergedHead
    Node list1Pointer = head1
    Node list2Pointer = head2
    if (Integer.parseInt(list1Pointer.data)
    <= Integer.parseInt(list2Pointer.data)) {
      mergedHead = list1Pointer
      list1Pointer = list1Pointer.next
    }
    else {
      mergedHead = list2Pointer
      list2Pointer = list2Pointer.next
    }
    Node current = mergedHead
    while (list1Pointer != null && list2Pointer != null) {
      if (Integer.parseInt(list1Pointer.data)
      <= Integer.parseInt(list2Pointer.data)) {
        current.next = list1Pointer
        list1Pointer = list1Pointer.next
      }
      else {
        current.next = list2Pointer
        list2Pointer = list2Pointer.next
      }
      current = current.next
    }
    if (list1Pointer == null) {
      current.next = list2Pointer
    }
    if (list2Pointer == null) {
      current.next = list1Pointer
    }
    return mergedHead
  }

  static void main(String[] args) {
    Node n11 = new Node('1')
    Node n12 = new Node('3')
    Node n13 = new Node('5')
    Node head1 = Node.buildList([n11, n12, n13])

    Node n21 = new Node('2')
    Node n22 = new Node('4')
    Node n23 = new Node('6')
    Node head2 = Node.buildList([n21, n22, n23])

    MergeSorted mergeSorted = new MergeSorted(head1, head2)
    Node.printList(head1)
    Node.printList(head2)
    Node mergedHead = mergeSorted.merge()
    Node.printList(mergedHead)
  }
}
