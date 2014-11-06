package com.trinarytree.operation;

import com.trinarytree.Node;

public class Operation {
	
	private static Node insert(Node root, int value) {
		if(root == null) {
			return new Node(value);
		}
		
		if(value < root.getData()) {
			root.setLeft(insert(root.getLeft(), value));
		} else if(value > root.getData()) {
			root.setRight(insert(root.getRight(), value));
		} else {
			root.setCenter(insert(root.getCenter(), value));
		}
		
		return root;
	}
	
	private static Node delete(Node root, int value) {
		if(root == null) {
			return null;
		}
		
		if(value < root.getData()) {
			root.setLeft(delete(root.getLeft(), value));
		} else if(value > root.getData()) {
			root.setRight(delete(root.getRight(), value));
		} else {
			// found the node to be deleted
			
			// case 1: it is a leaf node
			if(root.getLeft() == null && root.getRight() == null && root.getCenter() == null) {
				return null;
			}
			
			// case 2: it has a center child
			if(root.getCenter() != null) {
				Node centerChild = root.getCenter();
				root.setCenter(centerChild.getCenter());
				centerChild.setCenter(null);
				
				return root;
			}
			
			// case 3: it has both left and right children
			if(root.getLeft() != null && root.getRight() != null) {
				Node inorderSuccessor = findLeftmost(root.getRight());
				root.setData(inorderSuccessor.getData());
				root.setRight(delete(root.getRight(), inorderSuccessor.getData()));
				
				return root;
			}
			
			// case 4: it has either left or right child
			if((root.getLeft() != null && root.getRight() == null) || 
					(root.getLeft() == null && root.getRight() != null)) {
				
				if(root.getLeft() != null) {
					// only left child
					Node leftChild = root.getLeft();
					root.setLeft(null);
					
					return leftChild;
				} else if(root.getRight() != null) {
					// only right child
					Node rightChild = root.getRight();
					root.setRight(null);
					
					return rightChild;
				}
			}
		}
		
		return root;
	}
	
	private static Node findLeftmost(Node root) {
		if(root == null) {
			return null;
		}
		
		Node current = root;
		while(current.getLeft() != null) {
			current = current.getLeft();
		}
		
		return current;
	}
	
	private static void showTree(Node root) {
		if(root == null) {
			return;
		}
		
		showNode(root);
		showTree(root.getLeft());
		showTree(root.getCenter());
		showTree(root.getRight());
	}
	
	private static void showNode(Node node) {
		System.out.print("\n" + node.getData());
		if(node.getLeft() != null) {
			System.out.print(":" + node.getLeft().getData());
		}
		if(node.getCenter() != null) {
			System.out.print(":" + node.getCenter().getData());
		}
		if(node.getRight() != null) {
			System.out.print(":" + node.getRight().getData());
		}
	}
	
	public static void main(String args[]) {
		System.out.println("\n**********INSERTION**********");
		Node root = insert(null, 5);
		root = insert(root, 4);
		root = insert(root, 10);
		root = insert(root, 3);
		root = insert(root, 4);
		root = insert(root, 8);
		root = insert(root, 7);
		root = insert(root, 8);
		root = insert(root, 9);
		root = insert(root, 6);
		
		showTree(root);
		
		System.out.println("\n\n**********DELETION**********");
		//root = delete(root, 8);
		root = delete(root, 7);
		
		delete(null, 4);
		
		showTree(root);
	}
}
