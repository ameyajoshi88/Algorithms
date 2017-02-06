package com.linkedlist.stack

/**
 * @author Ameya
 * This program is used to implement a stack which supports the following operations:
 * 1. push
 * 2. pop
 * 3. top
 * The way this stack differs from normal stacks is that the effect of the operations on
 * the stack will persist only once the transaction in which they are performed is committed.
 * Transactions could be nested.
 * Thus, we will we have 3 more operations in stack
 * 4. begin
 * 5. commit
 * 6. rollback
 */

/**
 * Stack is implemented using a doubly linked list
 */
class TransactionalStack {

    Node top

    void printContents() {
        Node node = this.top
        if (node == null) {
            print('Stack is empty')
            return
        }
        print('TOP')
        while (node != null) {
            print(" -> ${node}")
            node = node.previous
        }
    }
}

class Transaction {

    Node top
    Node previousTop

    void begin(Node previousTop) {
        this.top = previousTop
        this.previousTop = previousTop
    }

    Node commit() {
        this.previousTop = this.top
        return this.previousTop
    }

    Node rollback() {
        if (this.previousTop != null) {
            this.previousTop.next = null
        }
        return this.previousTop
    }

    void push(String data) {
        Node node = new Node(data)
        // Not first element
        if (this.top != null) {
            this.top.next = node
        }
        node.previous = this.top
        this.top = node
    }

    void pop() {
        // No element
        if (this.top == null) {
            print 'Stack is empty'
        }
        else {
            this.top = this.top.previous
            this.top.next = null
        }
    }

    Node top() {
        return this.top
    }
}

class Node {

    Node(String data) {
        this.data = data
    }

    String data
    Node next
    Node previous

    String toString() {
        return this.data
    }
}

class TransactionalStackTester {
    static void main(String[] args) {
        TransactionalStack st = new TransactionalStack()
        Transaction t1 = new Transaction()
        t1.begin(st.top)
            t1.push('A')
            t1.push('B')
            t1.push('C')
            Transaction t12 = new Transaction()
            t12.begin(t1.top)
                t12.push('D')
                t12.pop()
                t12.pop()
            t1.top = t12.commit()
        st.top = t1.rollback()
        Transaction t2 = new Transaction()
        t2.begin(st.top)
            t2.push('E')
            Transaction t22 = new Transaction()
            t22.begin(t2.top)
                t22.push('F')
            t2.top = t22.commit()
        st.top = t2.commit()
        st.printContents()
    }
}

// Output:
//
// TOP -> F -> E
