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
  
  static void printListInReverse(Node head) {
    if (head == null) {
      return
    }
    printListInReverseHelper(head)
    print("${head.data} <- START")
  }
  
  static Node printListInReverseHelper(Node node) {
    if (node.next == null) {
      return node
    }
    Node headOfRest = printListInReverseHelper(node.next)
    print("${headOfRest.data} <- ")
    return node
  }
  
  static int length(Node head) {
    Node node = head
    int n = 0
    while (node != null) {
        n++
        node = node.next
    }
    return n
  }
}
