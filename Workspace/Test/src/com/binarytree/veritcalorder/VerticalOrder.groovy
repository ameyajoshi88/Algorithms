package com.binarytree.veritcalorder

/**
 * @author Ameya
 * This program is used to print the nodes of a binary tree in vertical order
 * @see <a href="http://www.geeksforgeeks.org/print-binary-tree-vertical-order/">Reference</a>
 *
 * We could use DFS or BFS to solve this. The problem will DFS is that we won't get the nodes in
 * proper vertical order within a column as we process the left of the tree before the right
 * BFS solves this issue as we move level wise down the tree
 */
class Node {

    int data
    Node left
    Node right

    Node(int data) {
        this.data = data
    }

}

class VerticalOrder {

    void verticalOrderDfs(Node root) {
        if (root == null) {
            println 'Empty tree'
            return
        }
        Map<Integer, List<Integer>> columns = new TreeMap<>()
        verticalOrderDfs(root, 0, columns)
        print(columns)
    }

    void verticalOrderDfs(Node node, int column, Map<Integer, List<Integer>> columns) {
        if (node != null) {
            List<Integer> nodes = columns.get(column)
            if (nodes == null) {
                nodes = []
                columns.put(column, nodes)
            }
            nodes.add(node.data)
            verticalOrderDfs(node.left, column - 1, columns)
            verticalOrderDfs(node.right, column + 1, columns)
        }
    }

    void verticalOrderBfs(Node root) {
        if (root == null) {
            println 'Empty tree'
            return
        }
        Map<Integer, List<Integer>> columns = new TreeMap<>()
        List<Object[]> queue = [[root, 0] as Object[]]
        while (!queue.isEmpty()) {
            Object[] element = queue.remove(0)
            Node node = element[0] as Node
            int column = element[1] as int
            List<Integer> nodes = columns.get(column)
            if (nodes == null) {
                nodes = []
                columns.put(column, nodes)
            }
            nodes.add(node.data)
            if (node.left != null) {
                queue.add([node.left, column - 1])
            }
            if (node.right != null) {
                queue.add([node.right, column + 1])
            }
        }
        print(columns)
    }

    void print(Map<Integer, List<Integer>> columns) {
        columns.each {
            List<Integer> nodes = it.value
            nodes.each { n -> print "$n " }
            print '\n'
        }
    }
}

class VerticalOrderTester {
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

        VerticalOrder verticalOrder = new VerticalOrder()
        println '--------------Using DFS------------------'
        verticalOrder.verticalOrderDfs(null)
        print '\n'
        verticalOrder.verticalOrderDfs(n1)
        println '--------------Using BFS------------------'
        verticalOrder.verticalOrderBfs(null)
        print '\n'
        verticalOrder.verticalOrderBfs(n1)

    }
}

// Output:
// --------------Using DFS------------------
// Empty tree
//
// 8
// 4
// 2 9
// 1 5 6
// 10 3 11
// 7
// 12
// --------------Using BFS------------------
// Empty tree
//
// 8
// 4
// 2 9
// 1 5 6
// 3 10 11
// 7
// 12















