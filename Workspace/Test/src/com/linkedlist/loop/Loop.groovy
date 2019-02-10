package com.linkedlist.loop

import groovy.transform.CompileStatic
import com.linkedlist.Node

/**
 * @author Ameya
 * This program detects whether a linked list contains a loop or not
 * calculates the length of the loop, detects the start of the loop
 * and removes the loop (if there exists one)
 */

@CompileStatic
class Loop {

  public Loop(Node head) {
    this.head = head
  }

  Node head

  /**
   * Checks if there is a loop
   * @return null if no loop is present
   *         Or the node at which the loop was detected
   */
  Node loopExists() {
    Node hare = head
    Node tortoise = head
    while (true) {
      if (hare == null || hare.next == null || hare.next.next == null) {
        return null // No loop
      }
      hare = hare.next.next
      tortoise = tortoise.next
      if (hare == tortoise) {
        return hare // Loop present
      }
    }
  }
  
  /**
   * @return length of the loop if it exists else returns null
   */
  Integer length() {
    Node loopDetectedAt = loopExists()
    if (loopDetectedAt == null) {
      return null
    }
    Node hare = loopDetectedAt.next
    Node tortoise = loopDetectedAt
    int n = 1
    while (hare != tortoise) {
      n++
      hare = hare.next
    }
    return n
  }
  
  /**
   * @return node at which the loop starts if it exists else returns null
   */
  Node startOfLoop() {
    Node loopDetectedAt = loopExists()
    if (loopDetectedAt == null) {
      return null
    }
    Node hare = loopDetectedAt
    Node tortoise = head
    while (hare != tortoise) {
      hare = hare.next
      tortoise = tortoise.next
    }
    return hare
  }
  
  /**
   * @return true after removing the loop 
   *         false if the loop does not exist
   */
  boolean removeLoop() {
    Node loopDetectedAt = loopExists()
    if (loopDetectedAt == null) {
      return false
    }
    Node hare = loopDetectedAt
    Node tortoise = head
    while (hare.next != tortoise.next) {
      hare = hare.next
      tortoise = tortoise.next
    }
    hare.next = null
    return true
  }

  static void main(String[] args) {
    Node head1 = Node.buildList(
      [new Node('A'), new Node('B'), new Node('C'), new Node('D')])
    assert new Loop(head1).loopExists() == null

    Node node1 = new Node('A')
    Node node2 = new Node('B')
    Node node3 = new Node('C')
    Node node4 = new Node('D')
    Node head2 = Node.buildList([node1, node2, node3, node4, node2])
    Loop loop2 = new Loop(head2)
    assert loop2.loopExists() != null
    assert 3 == loop2.length()
    assert node2 == loop2.startOfLoop()
    assert loop2.removeLoop()
    assert loop2.loopExists() == null
    
    println('Done')
  }
}
