package com.linkedlist

import groovy.transform.CompileStatic

@CompileStatic
class Node {
  
  Node(String data) {
    this.data = data
  }
    
  String data
  Node next
  
  static Node buildList(List<Node> nodes) {
    Node prev = null
    Node head = null
    nodes.each {
      if (head == null) {
        head = it
      }
      if (prev != null) {
        prev.next = it
      }
      prev = it
    }
    return head
  }
  
  static void printList(Node head) {
    Node node = head
    while (node != null) {
        print("${node.data} -> ")
        node = node.next
    }
    println('END')
  }
}
