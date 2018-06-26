# Cracking the Coding Interview

## Table of Contents
[**Chapter 1: Arrays and Strings**](#chapter-1-arrays-and-strings)  
[**Chapter 2: Linked Lists**](#chapter-2-linked-lists)  
[**Chapter 3: Stacks and Queues**](#chapter-3-stacks-and-queues)  
[**Chapter 4: Trees and Graphs**](#chapter-4-trees-and-graphs)  
[**Chapter 5: Bit Manipulation**](#chapter-5-bit-manipulation)

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

## Chapter 4: Trees and Graphs
Note: For tree and graph algorithms, the worst case and average case times may vary wildy, so we must evaluate both aspects of any algorithm.

### Types of Trees
* Each tree has a root node.
* The root node has zero or more child nodes.
* Each child node has zero or more child nodes, and so on.
* A node is called a "leaf" node if it has no children.

The tree cannot contain cycles. The nodes may or may not be in a particular order, they could have any data type as values, and they may or may not have links back to their parent nodes.

Tree and graph questions are rife with ambiguous details and incorrect assumptions. Be sure to watch out for the following issues and seek clarification when necessary.

#### Trees vs. Binary Trees
A binary tree is a tree in which each node has up to two children.

#### Binary Tree vs. Binary Search Tree
A binary search tree is a binary tree in which every node fits a specific ordering property: `all left descendents <= n < all right descendents`. This must be true for each node `n`.

Note: The definition of a binary search tree can vary slightly with respect to equality.

#### Balanced vs. Unbalanced
Note that balancing a tree does not mean that the left and right subtree are exactly the same size (which would be a "perfect binary tree").

One way to think about it is that a "balanced" tree really means something more like "not terribly imbalanced". It's balanced enough to ensure `O(log n)` times for `insert` and `find`, but it's not necessarily as balanced as it could be.

Two common types of balanced trees are red-black trees and AVL trees (discussed in the Advanced Topics section).

#### Complete Binary Trees
A complete binary tree is a binary tree in which every level of the tree is fully filled, except for perhaps the last level. To the extend that the last level is filled, it is filled left to right.

#### Full Binary Trees
A full binary tree is a binary tree in which every node has either zero or two children. That is, no nodes have only one child.

#### Perfect Binary Trees
A perfect binary tree is one where all leaf nodes are at the same level, and this level has the maximum number of nodes. Therefore, a perfect binary tree is both full and complete (Note that a full and complete binary tree is not necessarily a perfect binary tree).

A perfect tree must have exactly `2^k - 1` nodes (where `k` is the number of levels).

### Binary Tree Traversal
The most common tree traversal is in-order.

#### In-Order Traversal
In-order traversal means to "visit" the left branch, then the current node, and finally, the right branch.

```java
public class TreeTraversal {
    ...
    public void inOrderTraversal(TreeNode node) {
        if (node != null) {
            inOrderTraversal(node.left);
            visit(node);
            inOrderTraversal(node.right);
        }
    }
    ...
} 
```

#### Pre-Order Traversal
Pre-order traversal visits the current node before its child nodes (hence the name "pre-order").

```java
public class TreeTraversal {
    ...
    public void preOrderTraversal(TreeNode node) {
        if (node != null) {
            visit(node);
            inOrderTraversal(node.left);
            inOrderTraversal(node.right);
        }
    }
    ...
} 
```

In a pre-order traversal, the root is always the first node visited.

#### Post-Order Traversal
Post-order traversal visits the current node after its child nodes (hence the name "post-order").

```java
public class TreeTraversal {
    ...
    public void postOrderTraversal(TreeNode node) {
        if (node != null) {
            inOrderTraversal(node.left);
            inOrderTraversal(node.right);
            visit(node);
        }
    }
    ...
} 
```

In a post-order traversal, the root is always the last node visited.

### Binary Heaps (Min-Heaps and Max-Heaps)
A min-heap is a complete binary tree where each node is small than its children. The root, therefore, is the minimum element in the tree.

We have two key operations on a min-heap: `insert` and `extract_min`.

*insert*

We start by inserting into the last spot as to maintain the complete tree property.

Then, we "fix" the tree by swapping the new element with its parent, until we find an appropriate spot for the element. We essentially bubble up the minimum element. This takes `O(log n)` time, where `n` is the number of nodes in the heap.

*Extract Minimum Element*

First, we remove the minimum element and swap it with the last element in the heap. Then, we bubble down this element, swapping it with the smaller of its children until the min-heap property is restored. This will also take `O(log n)` time.

### Tries (Prefix Trees)
A trie is a variant of an n-ary tree in which characters are stored at each node. Each path down the tree may represent a word. Null nodes are often used to indicate complete words.

The actual implementation of these null nodes might be a special type of child, or we could use just a boolean flag `terminates` within the "parent" node.

A node in a trie could have anywhere from `1` through `ALPHABET_SIZE + 1` children (or, `0` through `ALPHABET_SIZE` if a boolean flag is used instead of a null node).

Very commonly, a trie is used to store the entire (English) laguage for quick prefix lookups. While a hash table can quickly look up whether a string is a valid word, it cannot tell us if a string is a prefix of any valid words.

A trie can check if a string is a valid prefix in `O(K)` time, where `K` is the length of the string. This is actually the same runtime as a hash table will take.

Many problems involving lists of valid words leverage a trie as an optimization. In situations when we search through the tree on related prefixes repeatedly, we might pass around a reference to the current node in the tree. This will allow us to just check if `Y` is a child of `MAN`, rather than starting from the root each time.

### Graphs
A graph is simply a collection of nodes with edges between (some of) them. If there is a path between every pair of vertices, it is called a "connected graph". An "acyclic graph" is one without cycles.

In terms of programming, there are two common ways to represent a graph.

#### Adjacency List
This is the most common way to represent a graph. Every vertex stores a list of adjacent vertices.

A simple class definition for a graph node could look essentially the same as a tree node.

```java
public class Graph {
    public Node[] nodes;
}

public class Node {
    public String name;
    public Node[] children;
}
```

An array (or a hash table) of lists (arrays, arraylists, linked lists, etc.) can store the adjacency list.

#### Adjacency Matrices
An adjaceny matrix is an `NxN` boolean matrix (where `N` is the number of nodes), where a `true` value at `matrix[i][j]` indicates an edge from node `i` to node `j`. In an undirected graph, an adjacency matrix will be symmetric.

Note that adjacency matrices are often less efficient than adjacency lists. In the adjacency list representation, you can easily iterate through the neighbors of a node. In the adjacency matrix representation, you will need to iterate through all the nodes to identify a node's neighbors.

### Graph Search
The two most common ways to search a graph are depth-first search (DFS) and breadth-first search (BFS).

In DFS, we start at the root (or another arbitrarily selected node) and explore each branch completely before moving on to the next branch.

In BFS, we start at the root (or another arbitrarily selected node) and explore each neighbor before going on to any of their children.

DFS is often preferred if we want to visit every node in the graph. However, if we want to find the shortest path (or just any path) between two nodes, BFS is generally better.

#### Depth-First Search (DFS)
Note that pre-order and other forms of tree traversal are a form of DFS. The key difference is that when implementing this algorithm, we must check if the node has been visited.

The pseudocode below implements DFS:

```java
public class DFS {
    ...
    public void search(Node root) {
        if (root == null) return;
        visit(root);
        root.visited = true;
        for (Node n : root.adjacent) {
            if (! n.visited) {
                search(n);
            }
        }
    }
    ...
}
```

#### Breadth-First Search (BFS)
The pseudocode below implements BFS:

```java
public class BFS {
    ...
    public void search(Node root) {
        Queue queue = new Queue();
        root.marked = true;
        queue.enqueue(root);

        while (! queue.isEmpty()) {
            Node r = queue.dequeue();
            visit(r);
            for (Node n : r.adjacent) {
                if (! n.marked) {
                    n.marked = true;
                    queue.enqueue(n);
                }
            }
        }
    }
    ...
}
```

#### Bidirectional Search
Bidirectional search is used to find the shortest path between a source and destination node. It operates by essentially running two simultaneous breadth-first searches, one from each node. When their searches collide, we have found a path.

To see why this is faster, consider a graph where every node has at most `k` adjacent nodes and the shortest path from node `s` to node `t` has length `d`.

* In traditional breadth-first search, this would take `O(k^d)` time.
* In bidirectional search, we have two searches that collide after approximately `d/2` levels. The search from node `s` visits approximately `k^(d/2)`, as does the search from node `t`. That approximately `2k^(d/2)`, or `O(k^(d/2))` nodes total.

The bidirectional search is faster by a factor of `k^(d/2)`! We can support paths that are twice as long.

## Chapter 5: Bit Manipulation
Bit manipulation is sometimes a useful technique to optimize your code.

### Bit Manipulation By Hand
Some tricks:
1. `0110 + 0110` is equivalent to `0110 * 2`, which is equivalent to shifting `0110` left by `1`.
2. `0100` equals `4`, and multiplying by `4` is just left shifting by `2`.
3. If you XOR a bit with its own negated value, you will always get `1`.

### Bit Facts and Tricks
We use `1s` and `0s` to indicate a sequence of 1s or 0s, respectively.

```
x ^ 0s = x          x & 0s = 0          x | 0s = x
x ^ 1s = ~x         x & 1s = x          x | 1s = 1s
x ^ x = 0           x & x = x           x | x = x
```

### Two's Complement and Negative Numbers
Computers typically store integers in two's complement representation. A positive number is represented as itself while a negative number is represented as the two's complement of its absolute value (with a 1 in its sign bit to indicate that it's a negative value). The two's complement of an N-bit number (where N is the number of bits used for the number, *including* the sign bit) is the complement of the number with respect to `2^(N-1)`.

In other words, the binary representation of `-k` (negative k) as an N-bit number is `concat(1, 2^(N-1) - k)`.

Another way to look at this is that we invert the bits in the positive representation and then add 1. 3 is `011` in binary. Flip the bits to get `100`, add 1 to get `101`, then prepend the sign bit (1) to get `1101`.

For one positive and one negative N-bit integer, `X` and `Y`, observe that if `X + |Y| = 2^(N-1)` then the binary values of `X` and `Y` are identical, other than the sign bit. E.g, `6 := 0110` and `-2 := 1110`.

### Arithmetic vs. Logical Right Shift
There are two types of right shift operators. The arithmetic right shift essentially divides by two. The logical right shift does what we would visually see as shifting the bits. This is best seen on a negative number.

In a logical right shift, we shift the bits and put a 0 in the most significant bit. It is indicated with a `>>>` operator.

In an arithmetic right shift, we shift values to the right but fill the new bits with the value of the sign bit. It is indicated by a `>>` operator.

### Common Bit Tasks
The following operations are very important to know.

#### Get Bit
This method shifts `1` over by `i` bits, creating a value like `00010000`. By performing an AND with `num`, we clear all bits other than the bit at bit `i`.

```
boolean getBit(int num, int i) {
    return ((num & (1 << i)) != 0);
}
```

#### Set Bit
This method shifts `1` over by `i` bits, creating a value like `00010000`. By performing an OR witj `num`, only the value at bit `i` will change. All other bits of the mask are zero and will not affect `num`.

```
int setBit(int num, int i) {
    return num | (1 << i);
}
```

#### Clear Bit
This method operates in almost the reverse of `setBit`. First, we create a number like `11101111`. Then, we perform an AND with num.

```
int clearBit(int num, int i) {
    int mask = ~(1 << i);
    return num & mask;
}
```

To clear all bits from the most significant bit through `i` (inclusive), we create a mask with a 1 at the ith bit (`1 << i`). Then we subtract `1` from it, giving us a sequence of 0s followed by `i` 1s. We then AND our number with this mask to leave just the last `i` bits.

```
int clearBitsMSBthroughI(int num, int i) {
    int mask = (1 << i) - 1;
    return num & mask;
}
```

To clear all bits from `i` through `0` (inclusive), we take a sequence of all 1s (which is -1) and shift left by `i + 1` bits. This gives us a sequence of 1s (in the most significant bits) followed by `i + 1` 0 bits.

```
int clearBitsIthrough0(int num, int i) {
    int mask = (-1 << (i + 1));
    return num & mask;
}
```

#### Update Bit
To set the ith bit to a value `v`, we first clear the bit at position `i`. Then, we shift the intended value, `v`, left by `i` bits. Finally, we OR these two numbers.

```
int updateBit(int num, int i, boolean bitIs1) {
    int value = bitIs1 ? 1 : 0;
    int mask = ~(1 << i);
    return (num & mask) | (value << i);
}
```
