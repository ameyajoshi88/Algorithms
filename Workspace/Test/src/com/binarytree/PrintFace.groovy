package com.binarytree

/**
 * @author Ameya
 * This is a program to traverse the 'face' of a binary tree from left to right
 * e.g.
 *                      1
 *                     / \
 *                    2   3
 *                   / \   \
 *                  4   5   6
 *
 *  Answer: 4, 2, 1, 3, 6
 */
class PrintFace {
    
    static void printFace(Node root) {
        if(root == null) {
            return
        }
        List<Integer> face = new ArrayList<>()
        printLeft(root.left, face)
        face << root.data
        printRight(root.right, face)
        print(face.join(','))
    }

    static void printLeft(Node node, List<Integer> face) {
        if(node == null) {
            return
        }
        printLeft(node.left, face)
        face << node.data
    }

    static void printRight(Node node, List<Integer> face) {
        while(node != null) {
            face << node.data
            node = node.right
        }
    }

    static void main(String[] args) {
        Node a = new Node('A' as char)
        Node b = new Node('B' as char)
        Node c = new Node('C' as char)
        Node d = new Node('D' as char)
        Node e = new Node('E' as char)
        Node f = new Node('F' as char)
        Node g = new Node('G' as char)
        Node h = new Node('H' as char)
        Node i = new Node('I' as char)
        Node j = new Node('J' as char)
        Node k = new Node('K' as char)
        Node l = new Node('L' as char)

        a.left = b
        a.right = c

        b.left = d
        b.right = e

        c.left = f
        c.right = g

        d.left = h

        e.left = i
        e.right = j

        g.left = k
        g.right = l
        
        printFace(a)
    }
}
