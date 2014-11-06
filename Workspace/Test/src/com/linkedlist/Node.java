package com.linkedlist;

public class Node {
	public Node(String data) {
		this.data = data;
	}
	private String data;
	private Node next;
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Node getNext() {
		return next;
	}
	public void setNext(Node next) {
		this.next = next;
	}
	
	
}
