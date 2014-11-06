package com.linkedlist.palindrome;

import com.linkedlist.Node;

public class Palindrome {
	public static void main(String args[]) {
		Node n1 = new Node("A");
		Node n2 = new Node("B");
		Node n3 = new Node("B");
		Node n4 = new Node("A");
		//Node n5 = new Node("B");
		
		n1.setNext(n2);
		n2.setNext(n3);
		n3.setNext(n4);
		//n4.setNext(n5);
		
		System.out.println(isPalindromeRecursive(n1));
	}
	
	private static boolean isPalindromeRecursive(Node node) {
		return (isPalindromeRecursive(node, "") != null);
	}
	
	private static String isPalindromeRecursive(Node node, String s) {
		if(node == null) {
			return s;
		}
		s += node.getData();
		
		String result = isPalindromeRecursive(node.getNext(), s);
		System.out.println("node:" + node.getData() + " s:" +result);
		if(result != null && result.substring(0, 1).equals(node.getData())) {
			return result.substring(1);
		} else {
			return null;
		}
	}
}
