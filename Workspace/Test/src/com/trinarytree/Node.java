package com.trinarytree;

public class Node {
	private int data;
	private Node left;
	private Node center;
	private Node right;
	
	public Node() {
		
	}
	public Node(int data) {
		this.data = data;
	}
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	public Node getLeft() {
		return left;
	}
	public void setLeft(Node left) {
		this.left = left;
	}
	public Node getCenter() {
		return center;
	}
	public void setCenter(Node center) {
		this.center = center;
	}
	public Node getRight() {
		return right;
	}
	public void setRight(Node right) {
		this.right = right;
	}
	
	
}
