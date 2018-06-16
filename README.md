# Cracking the Coding Interview
Interview Prep

## Chapter 1: Arrays and Strings

#### Hash Tables:
A hash table is a data structure that maps keys to values for highly efficient lookup through the use of a hash code function. Note that two different keys could have the same hash code, as there may be an infinite number of keys and a finite number of ints. If the number of collisions is very high, the worst case runtime is `O(N)`; generally, lookup time is `O(1)`.

#### ArrayList & Resizable Arrays
ArrayLists (and resizable arrays) have amortized insertion time of `O(1)`.

#### StringBuilder
Naively concatenating n strings runs in `O(xn^2)`, assuming that all strings have a length of `x`. On each concatenation, a new copy of the string is created. `Stringbuilder` simply creates a resizable array of all the strings, copying them back to a string only when necessary.

## Chapter 2: Linked Lists 
Benefit of linked list over array is that you can add and remove items from the beginning of the list in constant time.

#### Creating a Linked List
The code below implements a very basic singly linked list.

```java
class Node {
    Node next = null;
    int data;

    public Node(int d) {
        data = d;
    }

    void appendToTail(int d) {
        Node end = new Node(d);
        Node n = this;

        while (n.next != null) {
            n = n.next;
        }
        n.next = end;
    }
}
```

#### Deleting a Node from a Singly Linked List
Given a node `n`, we find the previous node `prev` and set `prev.next` equal to `n.next`. If the list is doubly linked, we must also update `n.next` to set `n.next.prev` equal to `n.prev`. Important things to remember:
1. Check for the null pointer.
2. Update the head or tail pointer as necessary.

```java
public class Node {
    ...
    Node deleteNode(Node head, int d) {
        Node n = head;

        if (n.data == d)
            return head.next; /* moved head */

        while (n.next != null) {
            if (n.next.data == d) {
                n.next = n.next.next;
                return head; /* head didn't change */
            }
            n = n.next;
        }
        return head;
    }
    ...
}
```


#### The "Runner" Technique
The runner technique means that you iterate through the linked list with two pointers simultaneously, with one ahead of the other. The "fast" node might be ahead by a fixed amount, or it might be hopping multipke nodes for each one node that the "slow" node iterates through.

#### Recursive Problems
Explained in further detail later one. Recursive algorithms take at least `O(n)` space, where `n` is the depth of the recursive call.

## Chapter 3: Stacks and Queues

#### Implementing a Stack
Stacks use the following operations:
* `pop()`: Remove the top item from the stack.
* `push(item)`: Add an item to the top of the stack.
* `peek()`: Return the top of the stack.
* `isEmpty()`: Return true if and only if the stack is empty.

One case where stacks are often useful is in certain recursive algorithms. Sometimes you need to push temporary data onto a stack as you recurse, but then remove them as you backtrack.

A stack can also be used to implement a recursive algorithm iteratively.

Sample code to implement a stack:

```java
public class MyStack<T> {
    private static class StackNode<T> {
        private T data;
        private StackNode<T> next;

        public StackNode(T data) {
            this.data = data;
        }
    }

    private StackNode<T> top;

    public T pop() {
        if (top == null) throw new EmptyStackException();
        T item = top.data;
        top = top.next;
        return item;
    }

    public void push(T item) {
        StackNode<T> t = new StackNode<T>(item);
        t.next = top;
        top = t;
    }

    public T peek() {
        if (top == null) throw new EmptyStackException();
        return top.data;
    }

    public boolean isEmpty() {
        return top == null;
    }
}
```

#### Implementing a Queue
Queues use the following operations:
* `add(item)`: Add an item to the end of the list.
* `remove()`: Remove the first item in the list.
* `peek()`: Return the top of the queue.
* `isEmpty()`: Return true if and only if the queue is empty.

One place where queues are often used is in breadth-first search or in implementing a cache.

Sample code to implement a queue:

```java
public class MyQueue<T> {
    private static class QueueNode<T> {
        private T data;
        private QueueNode<T> next;

        public QueueNode(T data) {
            this.data = data;
        }
    }

    private QueueNode<T> first;
    private QueueNode<T> last;

    public void add(T item) {
        QueueNode t = new QueueNode(item);
        if (last != null) last.next = t;
        if (first == null) first = t;
        last = t;
    }

    public T remove() {
        if (first == null) throw new NoSuchElementException();
        T data = first.data;
        first = first.next;
        if (first == null) last = null;
        return data;
    }

    public T peek() {
        if (first == null) throw new NoSuchElementException();
        return first.data;
    }

    public boolean isEmpty() {
        return first == null;
    }
}
```
