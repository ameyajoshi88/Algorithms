package com.binarytree.pathstoleaves

/**
 * @author Ameya
 * This program is used to find the paths from root to all the leaf nodes
 */
class Node {

    int data
    Node left
    Node right

    Node(int data) {
        this.data = data
    }

}
class PathsToLeaves {

    List<List<Integer>> pathsToLeaves(Node root) {
        List<List<Integer>> paths = []
        pathsToLeaves(root, [], paths)
        return paths
    }

    void pathsToLeaves(Node root, List<Integer> path, List<List<Integer>> paths) {
        path.add(root.data) // add current node to path
        if(root.left == null && root.right == null) {
            paths.add(path.collect()) // encountered root, so record path
        }
        if(root.left != null) {
            pathsToLeaves(root.left, path, paths) // traverse left
        }
        if(root.right != null) {
            pathsToLeaves(root.right, path, paths) // traverse right
        }
        path.remove(path.size() - 1) // remove current node from path
    }

}

class PathsToLeavesTester {
    static void main(String[] args) {
        Node n1 = new Node(1)
        Node n2 = new Node(2)
        Node n3 = new Node(3)
        Node n4 = new Node(4)
        Node n5 = new Node(5)
        Node n6 = new Node(6)
        Node n7 = new Node(7)
        Node n8 = new Node(8)
        Node n9 = new Node(9)
        Node n10 = new Node(10)
        Node n11 = new Node(11)
        Node n12 = new Node(12)

        n1.left = n2
        n1.right = n3

        n2.left = n4
        n2.right = n5

        n3.left = n6
        n3.right = n7

        n4.left = n8

        n5.left = n9
        n5.right = n10

        n7.left = n11
        n7.right = n12

        PathsToLeaves pathsToLeaves = new PathsToLeaves()
        print pathsToLeaves.pathsToLeaves(n1)
    }
}
