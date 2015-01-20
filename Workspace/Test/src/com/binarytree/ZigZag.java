package com.binarytree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
/**
 * 
 * @author Ameya
 * This is a program to traverse a binary tree levelwise in zig-zag order
 * zig-zag:- Root is at level 1. 
 * 			 Traverse odd level nodes from right to left
 * 			 Traverse even level nodes from left to right
 *
 */
public class ZigZag {
	
	private static void zigZagTraversal(Node root) {
		boolean isLevelOdd = true;
		
		Queue<Node> toVisit = new LinkedList<Node>();
		Stack<Node> levelNodes = new Stack<Node>();
		toVisit.add(root);
		System.out.print(root.getData());
		
		while(!toVisit.isEmpty() || !levelNodes.isEmpty()) {
			Node node = toVisit.remove();
			
			if(isLevelOdd) {
				if(node.getRight() != null) {
					levelNodes.push(node.getRight());
				}
				if(node.getLeft() != null) {
					levelNodes.push(node.getLeft());
				}
			} else {
				if(node.getLeft() != null) {
					levelNodes.push(node.getLeft());
				}
				if(node.getRight() != null) {
					levelNodes.push(node.getRight());
				}
			}
			
			if(toVisit.isEmpty()) {
				isLevelOdd = !isLevelOdd;
				System.out.print("\n");
				while(!levelNodes.isEmpty()) {
					System.out.print(levelNodes.peek().getData() + " ");
					toVisit.add(levelNodes.pop());
				}
			}
		}
	}
	
	public static void main(String args[]) {
		Node a = new Node('A');
		Node b = new Node('B');
		Node c = new Node('C');
		Node d = new Node('D');
		Node e = new Node('E');
		Node f = new Node('F');
		Node g = new Node('G');
		Node h = new Node('H');
		Node i = new Node('I');
		Node j = new Node('J');
		Node k = new Node('K');
		Node l = new Node('L');
		
		a.setLeft(b);
		a.setRight(c);
	
		b.setLeft(d);
		b.setRight(e);
		
		c.setLeft(f);
		c.setRight(g);
		
		d.setLeft(h);
		
		e.setLeft(i);
		e.setRight(j);
		
		g.setLeft(k);
		g.setRight(l);
		
		zigZagTraversal(a);
	}
}
