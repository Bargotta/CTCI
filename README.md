# Cracking the Coding Interview

## Table of Contents
[**Chapter 1: Arrays and Strings**](#chapter-1-arrays-and-strings)  
[**Chapter 2: Linked Lists**](#chapter-2-linked-lists)  
[**Chapter 3: Stacks and Queues**](#chapter-3-stacks-and-queues)  
[**Chapter 4: Trees and Graphs**](#chapter-4-trees-and-graphs)  
[**Chapter 5: Bit Manipulation**](#chapter-5-bit-manipulation)  
[**Chapter 6: Math and Logic Puzzles**](#chapter-6-math-and-logic-puzzles)  
[**Chapter 7: Object-Oriented Design**](#chapter-7-object-oriented-design)  
[**Chapter 8: Recursion and Dynamic Programming**](#chapter-8-recursion-and-dynamic-programming)  
[**Chapter 9: System Design and Scalability**](#chapter-9-system-design-and-scalability)  
[**Chapter 10: Sorting and Searching**](#chapter-10-sorting-and-searching)  
[**Chapter 11: Testing**](#chapter-11-testing)  
[**Chapter 13: Java**](#chapter-13-java)  
[**Chapter 14: Databases**](#chapter-14-databases)  
[**Chapter 15: Threads and Locks**](#chapter-15-threads-and-locks)  
[**Advanced Topics**](#advanced-topics)

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

## Chapter 6: Math and Logic Puzzles

### Prime Numbers
Every positive integer can be decomposed into a product of primes.

#### Divisibility
The prime number law stated above means that, in order for a number `y` to divide a number `x` (`mod(x, y) = 0`), all primes in `y`'s prime factorization must be in `x`'s prime factorization.

```
let x = 2^(j0) * 3^(j1) * 5^(j2) * 7^(j3) * 11^(j4) * ...
let y = 2^(k0) * 3^(k1) * 5^(k2) * 7^(k3) * 11^(k4) * ...

gcd(x, y) = 2^min(j0) * 3^min(j1) * 5^min(j2) * ...
lcm(x, y) = 2^max(j0) * 3^max(j1) * 5^max(j2) * ...

gcm * lcm = 2^(j0 + k0) * 3^(j1 + k1) * ...
          = 2^(j0) * 2^(k0) * 3^(j1) * 3^(k1) * ...
          = xy
```

#### Checking for Primality
The naive way is to simply iterate from `2` through `n - 1`, checking for divisibility on each iteration.

```java
public class Primality {
    boolean primeNaive(int n) {
        if (n < 2) return false;

        for (int i = 2; i < n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}
```

A small but important improvement is to iterate only up through the square root of `n`.

```java
public class Primality {
    boolean primeSlightlyBetter(int n) {
        if (n < 2) return false;

        int sqrt = (int) Math.sqrt(n);
        for (int i = 2; i <= sqrt; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}
```

In reality, all we *really* need to do is check if `n` is divisible by a prime number.

#### Generating a List of Primes: The Sieve of Eratosthenes
The Sieve of Eratosthenes is a highly efficient way to generate a list of primes. It works by recognizing that all non-prime numbers are divisible by a prime number.

We start with a list of all the numbers up through some value `max`. First, we cross of all numbers divisible by `2`. Then, we look for the next prime (the next non-crossed off number) and cross off all numbers divisible by it. By crossing off all numbers divisible by `2, 3, 5, 7, 11`, and so on, we wind up with a list of prime numbers from `2` through `max`.

```java
public class Primality {
    boolean[] sieveOfEratosthenes(int max) {
        boolean[] flags = new boolean[max + 1];
        int count = 0;

        init(flags); // Set all flags to true other than 0 and 1
        int prime = 2;

        while (prime <= Math.sqrt(max)) {
            /* Cross of remaining multiples of prime */
            crossOff(flags, prime);

            /* Find next value which is true */
            prime = getNextPrime(flags, prime);
        }

        return flags;
    }

    void crossOff(boolean[] flags, int prime) {
        /* Cross off remaining multiples of prime. We can start with (prime*prime),
         * because if we have a k * prime, where k < prime, this value would have
         * already been crossed off in a prior iteration. */
        for (int i = prime * prime; i < flags.length; i += prime) {
            flags[i] = false;
        }
    }

    int getNextPrime(boolean[] flags, int prime) {
        int next = prime + 1;
        while (next < flags.length && !flags[next]) {
            next++;
        }
        return next;
    }
}
```

Of course, there are a number of optimizations that can be made to this. One simple one is to only use odd numbers in the array, which would allow us to reduce our space useage by half.

### Probability

#### Probability of A and B
`P(A and B) = P(B given A) P(A)`

Bayes' Theorem: `P(A given B) = P(B given A) P(A) / P(B)`

#### Probability of A or B
`P(A or B) = P(A) + P(B) - P(A and B)`

From here, getting the special case rules for independent events and for mutually exclusive events is easy.

#### Independence
`P(A and B) = P(A) P(B)`. This comes from recognizing that `P(B given A) = P(B)`, since A indicates nothing about B.

"One happening tells you nothing about the other happening"

#### Mutual Exclusivity
`P(A or B) = P(A) + P(B)`

"If one happens, then the other cannot happen"

Two events cannot be both independent and mutually exclusive (provided both have probabilities greater than 0).

### Start Talking
Start talking, and show the interviewer how you approach a problem.

### Develop Rules and Patterns
In many cases, you will find it useful to write down "rules" or patterns that you discover while solving the problem.

### Worst Case Shifting
Many brainteasers are worst-case minimization problems, worded either in terms of *minimizing* an action or in doing something at most a specific number of times. A usefule technique is to try to "balance" the worst case. That is, if an early decision results in skewing of the worst case, we can sometimes change the decision to balance out the worst case.

### Algorithm Approaches
Brainteasers are often nothing more than algorithm questions with the technical aspects removed.

## Chapter 7: Object-Oriented Design

### How to Approach

#### Step 1: Handle Ambiguity
Object-oriented design questions are often intentionally vague in order to test whether you'll make assumptions or if you'll ask clarifying questioins.

When being asked an object-oriented design question, you should inquire *who* is going to use it and *how* they are going to use it. Depending on the question, you may even want to go through the "six Ws": who, what, where, when, how, why.

#### Step 2: Define the Core Objects
Consider what the "core objects" in a system are. 

#### Step 3: Analyze Relationships
We now want to analyze the relationships between the objects. Which objects are members of which other objects? Do any objects inherit from any others? Are relationships many-to-many or one-to-many?

#### Step 4: Investigate Actions
What remains is to consider the key actions that the objects will take and how they relate to each other.

### Design Patterns

#### Singleton Class
The Singleton pattern ensures that a class has only one instance and ensures access to the instance through the application. It can be useful in cases when you have a "global" object with exactly one instance.

```java
public class Restaurant {
    private static Restaurant _instance = null;
    protected Restaurant() { ... }
    public static Restaurant getInstance() {
        if (_instance == null) {
            _instance = new Restaurant();
        }
        return _instance;
    }
}
```

It should be noted that many people dislike the Singleton design pattern, even calling it an "anti-pattern". One readon for this is that it can interfere with unit testing.

#### Factory Method
The Factory Method offers an interface for creating an instance of a class, with its subclasses deciding which class to instantiate. You might want to implement this with the creator class being abstract and not providing an implementation for the Factory method. Or, you could have the Creator class be a concrete class that provides an implementation for the Factory method. In this case, the Factory method would take a parameter representing which class to instantiate.

```java
public class CardGame {
    public static CardGame createCardGame(GameType type) {
        if (type == GameType.Poker)
            return new PokerGame();
        else if (type == GameType.BlackJack)
            return new BlackJackGame();
        return null;
    }
}
```

## Chapter 8: Recursion and Dynamic Programming
When you hear a problem beginning with the following statements, it's often a good candidate for recursion: "Design an algorithm to compute the nth...", "Write code to list the first n...", "Implement a method to compute all...", and so on.

### How to Approach
Recursive solutions, by definition, are built off of solutions to subproblems. There are many ways you might divide a problem into subproblems, including bottom-up, top-down, and half-and-half.

#### Bottom-Up Approach
The bottom-up approach is often the most intuitive. We start with knowing how to solve the problem for a simple case, like a list with one element. Then we figure out how to solve the problem for two elements, then for three elements, and so on. The key here is to think about how you can *build* the solution for one case off of the previous case (or multiple previous cases).

#### Top-Down Approach
The top-down approach can be more complex since it's less concrete. But sometimes, it's the best way to think about the problem.

In these problems, we think about how we can divide the problem for case N into subproblems.

Be careful of overlap between the cases.

#### Half-and-Half Approach
It's often effective to divide the data set in half.

For example, binary search works with a "half-and-half" approach.

### Recursive vs. Iterative Solutions
Each recursive call adds a new layer to the stack, which means that if your algorithm recurses to a depth of `n`, it uses at least `O(n)` memory.

For this reason, it's often better to implement a recursive algorithm iteratively. *All* recursive algorithms can be implemented iteratively, although sometimes the code to do so is much more complex.

### Dynamic Programming & Memoization
Dynamic programming is mostly just a matter of taking a recursive algorithm and finding the overlapping subproblems (that is, the repeated calls). You then cache those results for future recursive calls.

> A note on terminology: Some people call top-down dynamic programming "memoization" and only use "dynamic programming" to refer to bottom-up work. We do not make such a distinction here. We call both dynamic programming.

One of the simplest examples of dynamic programming is computing the nth Fibonacci number.

#### Fibonacci Numbers

*Recursive:* `O(2^n)`

```
int fibonacci(int i) {
    if (i == 0 || i == 1) return i;
    return fibonacci(i - 1) + fibonacci(i - 2);
}
```

*Top-Down Dynamic Programming (or Memoization):* `O(n)`


```
int fibonacci(int n) {
    return fibonacci(n, new int[n + 1]);
}

int fibonacci(int i, int[] memo) {
    if (i == 0 || i == 1) return i;

    if (memo[i] == 0) {
        memo[i] = fibonacci(i - 1, memo) + fibonacci(i - 2, memo);
    }
    return memo[i];
}
```

*Bottom-Up Dynamic Programming:* `O(n)`


```
int fibonacci(int n) {
    if (n == 0 || n == 1) return n;

    int[] memo = new int[n];
    memo[0] = 0;
    memo[1] = 1;
    for (int i = 2; i < n; i++) {
        memo[i] = memo[i - 1] + memo[i - 2];
    }
    return memo[n - 1] + memo[n - 2];
}
```

If you really think about how this works, you only use `memo[i]` for `memo[i + 1]` and `memo[i + 2]`. You don't need it after that.

```
int fibonacci(int n) {
    if (n == 0) return 0;
    int a = 0;
    int b = 1;
    for (int i = 2; i < n; i++) {
        int c = a + b;
        a = b;
        b = c;
    }
    return a + b;
}
```

## Chapter 9: System Design and Scalability

### Handling the Questions
* Communicate
* Go broad first
* Use the whiteboard
* Acknowledge interviewer concerns
* Be careful about assumptions
* State your assumptions explicitly
* Estimate when necessary
* Drive

### Design: Step-By-Step

#### Step 1: Scope the Problem

#### Step 2: Make Reasonable Assumptions

#### Step 3: Draw the Major Components
It may be helpful to ignore major scalability challenges and just pretend that the simple, obvious approaches will be okay. You'll handle the big issues in Step 4.

#### Step 4: Identify the Key Issues
What will be the bottlenecks or major challenges in the system?

#### Step 5: Redesign for the Key Issues

### Algorithms that Scale: Step-By-Step
In some cases, you're not being asked to design an entire system. You're just being asked to design a single feature or algorithm, but you have to do it in a scalable way.

#### Step 1: Ask Questions

#### Step 2: Make Believe
Pretend that the data can all fit on one machine and there are no memory limitations.

#### Step 3: Get Real

#### Step 4: Solve Problems

### Key Concepts
All of these are deep, complex topics, so we encourage you to use online resources for more research.

#### Horizontal vs. Vertical Scaling
A system can be scaled one of two ways:

* Vertical scaling means increasing the resources of a specific node. For example, you might additional memory to a server to improve its ability to handle load changes.
* Horizontal scaling means increasing the number of nodes. For example, you might add additional servers, thus decreasing the load on any one server.

Vertical scaling is generally easier than horizontal scaling, but it's limited. You can only add so much memory or disk space.

#### Load Balancer
Typically, some frontend parts of a scalable website will be thrown behind a load balancer. This allows a system to distribute the load evenly so that one server doesn't crash and take down the whole system. To do so, of course, you have to build out a network of cloned servers that all have essentially the same code and access to the same data.

#### Database Denormalization and NoSQL
Joins in a relational database such as SQL can get very slow as the system grows bigger. For this reason, you would generally avoid them.

Denormalization is one part of this. Denormalization means adding redundant information into a database to speed up reads. Or, you can go with a NoSQL database. A NoSQL database does not support joins and might structure data in a different way. It is designed to scale better.

#### Database Partitioning (Sharding)
Sharding means splitting the data across multiple machines while ensuring you have a way of figuring out which data is on which machine.

A few commons ways of partitioning include:

* **Vertical Partitioning**: This is basically partitioning by feature. One drawback of this is that if one of these tables gets very large, you might need to repartition that database (possibly using a different partitioning scheme).

* **Key-Based (or Hash-Based) Partitioning**: This uses some part of the data (for example an ID) to partition it. A very simple way to do this is to allocate N servers and put the data on `mod(key, n)`. One issue with this is that the number of servers you have is effectively fixed. Adding additional servers means reallocating all the data - a very expensive task.

* **Directory-Based Partitioning**: In this scheme, you maintain a lookup table for where the data can be found. This makes it relatively easy to add additional servers, but it comes with two major drawbacks. First, the lookup table can be a single point of failure. Second, constantly accessing this table impacts performance.

Many architects actually end up using multiple partitioning schemes.

#### Caching
An in-memory cache can deliver very rapid results. It is a simple key-value pairing and typically sits between your application layer and your data store.

When you cache, you might cache a query and its results directly. Or, alternatively, you can cache the specific object (for example, a rendered version of part of the website).

#### Asynchronous Processing & Queues
Slow operations should ideally be done asynchronously. Otherwise, a user might get stuck waiting and waiting for a process to complete.

In some cases, we can do this in advance (i.e., we can pre-process). For example, we might have a queue of jobs to be done that update some part of the website.

In other cases, we might tell the user to wait and notify them when the process is done.

#### Networking Metrics
Some of the most important metrics around networking include:

* **Bandwidth**: This is the maximum amount of data that can be transferred in a unit of time. It is typically expressed in bits per second.
* **Throughput**: Whereas bandwidth is the maximum data that can be transferred in a unit of time, throughput is the actual amount of data that is transferred.
* **Latency**: This is how long it takes data to go from one end to the other. That is, it is the delay between the sender sending information and the receiver receiving it.

Latency can be easy to disregard, but it can be very important in particular situations (for example, in online games). However, there is often little you can do about latency.

#### MapReduce
A MapReduce program is typically used to process large amounts of data. As its name suggests, a MapReduce program requires you to write a Map step and a Reduce step. The rest is handled by the system.

* Map takes in some data and emits a `<key, value>` pair.
* Reduce takes a key and a set of associated values and "reduces" them in some way, emitting a new key and value. The results of this might be fed back into the Reduce program for more reducing.

MapReduce allows us to do a lot of processing in parallel, which makes processing huge amounts of data more scalable.

### Considerations
In addition to the earlier concepts to learn, you should consider the following issues when designing a system.

* **Failures**
* **Availability and Reliability**: Availability is a function of the percentage of time the system is operational. Reliability is a function of the probability that the system is operational for a certain unit of time.
* **Read-heavy vs. Write-heavy**: If it's write-heavy, you could consider queuing up the writes (but think about potential failure here!). If it's read-heavy, you might want to cache. Other design decisions could change as well.
* **Security**

### There is no "Perfect" system
There are always tradeoffs. Understand use cases, scope a problem, make reasonable assumptions, create a solid design based on those assumptions, and be open about the weaknesses of your design.

## Chapter 10: Sorting and Searching

### Common Sorting Algorithms
Of the five algorithms explained below, Merge Sort, Quick Sort, and Bucket Sort are the most commonly used in interviews.

#### Bubble Sort | Runtime: `O(n^2)` average and worst case. Memory: `O(1)`.
We start at the beginning of the array and swap the first two elements if the first is greater than the second. Then, we go to the next pair, and so on, continuously making sweeps of the array until it is sorted. In doing so, the smaller items slowly "bubble" up to the beginning of the list.

#### Selection Sort | Runtime: `O(n^2)` average and worst case. Memory: `O(1)`.
Selection Sort is the child's algorithm: simple, but inefficient. Find the smallest element using a linear scan and move it to the front (swapping it with the front element). Then, find the second smallest and move it, again doing a linear scan. Continue doing this until all the elements are in place.

#### Merge Sort | Runtime: `O(n log(n))` average and worst case. Memory: `Depends`.
Merge sort divides the array in half, sorts each of those halves, and then merges them back together. Each of those halves has the same sorting algorithm applied to it. Eventually, you are merging just two single-element arrays. It is the "merge" part that does all the heavy lifting.

The merge method operates by copying all the elements from the target array segment into a helper array, keeping track of where the start of the left and right halves should be (`helperLeft` and `helperRight`). We then iterate through `helper`, copying the smaller element from each half into the array. At the end, we copy any remaining elements into the target array.

```java
public class MergeSort {
    void mergesort(int[] array) {
        int[] helper = new int[array.length];
        mergesort(array, helper, 0, array.length - 1);
    }

    void mergesort(int[] array, int[] helper, int low, int high) {
        if (low < high) {
            int middle = (low + high) / 2;
            mergesort(array, helper, low, middle); // Sort left half
            mergesort(array, helper, middle + 1, high); // Sort right half
            merge(array, helper, low, middle, high); // Merge them
        }
    }

    void merge(int[] array, int[] helper, int low, int middle, int high) {
        /* Copy both halves into a helper array */
        for (int i = low; i <= high; i++) {
            helper[i] = array[i];
        }

        int helperLeft = low;
        int helperRight = middle + 1;
        int current = low;

        /* Iterate through helper array. Compare the left and right half, copying back
         * the smaller element from the two halves into the original array. */
        while (helperLeft <= middle && helperRight <= high) {
            if (helper[helperLeft] <= helper[helperRight]) {
                array[current] = helper[helperLeft];
                helperLeft++;
            } else {
                array[current] = helper[helperRight];
                helperRight++;
            }
            current++;
        }

        /* Copy the rest of the left side of the array into the target array */
        int remaining = middle - helperLeft;
        for (int i = 0; i <= remaining; i++) {
            array[current + i] = helper[helperLeft + i];
        }
    }
}
```

The space complexity of merge sort is `O(n)` due to the auxiliary space used to merge parts of the array.

#### Quick Sort | Runtime: `O(n log(n))` average, `O(n^2)` worst case. Memory: `O(log(n))`.
In quick sort, we pick a random element and partition the array, such that all numbers that are less than the partitioning element come before all elements that are greater than it. The partitioning can be performed efficiently through a series of swaps.

If we repeatedly partition the array (and its sub-arrays) around an element, the array will eventually become sorted. However, as the partitioned element is not guaranteed to be the median (or anywhere near the median), our sorting could take `O(n^2)` time.

```java
public class QuickSort {
    void quickSort(int[] arr, int left, int right) {
        int index = partition(arr, left, right);
        if (left < index - 1) quickSort(arr, left, index - 1);
        if (index < right) quickSort(arr, index, right);
    }

    int partition(int[] arr, int left, int right) {
        int pivot = arr[(left + right) / 2]; // Pick pivot point
        while (left <= right) {
            // Find element on left that should be on right
            while (arr[left] < pivot) left++;

            // Find element on right that should be on left
            while (arr[right] > pivot) right--;

            // Swap elements, and move left and right indices
            if (left <= right) {
                swap(arr, left, right); // swaps elements
                left++;
                right--;
            }
        }
        return left;
    }
}
```

#### Radix Sort | Runtime: `O(kn)`.
Radix sort is a sorting algorithm for integers (and some other data types) that takes advantage of the fact that integers have a finite number of bits. In radix sort, we iterate through each digit of the number, grouping numbers by each digit. For example, if we have an array of integers, we might first sort by the first digit, so that the 0s are grouped together. Then, we sort each of these groupings by the next digit. We repeat this process sorting by each subsequent digit, until finally the whole array is sorted.

Unlike comparison sorting algorithms, which cannot perform better than `O(n log(n))` in the average case, radix sort has a runtime of `O(kn)`, where `n` is the number of elements and `k` is the number of passes of the sorting algorithm.

### Searching Algorithms
In binary search, we look for an element x in a sorted array by first comparing x to the midpoint of the array. If x is less than the midpoint, then we search the left half of the array. If x is greater than the midpoint, then we search the right half of the array. We then repeat this process, treating the left and right halves as subarrays until we either find x or the subarray has size 0.

```java
public class BinarySearch {
    int binarySearch(int[] a, int x) {
        int low = 0;
        int high = a.length - 1;
        int mid;

        while (low <= high) {
            mid = (low + high) / 2;
            if (a[mid] < x) low = mid + 1;
            else if (a[mid] > x) high = mid - 1;
            else return mid;
        }
        return -1;
    }
}
```

## Chapter 11: Testing
Testing problems usually fall under one of four categories: (1) Test a real world object (like a pen); (2) Test a piece of software; (3) Write test code for a function; (4) Troubleshoot an existing issue.

### What the Interviewer Is Looking For
Interviewers want to test the following:

* *Big Picture Understanding:* Are you a person who understands what the software is really about? Can you prioritize test cases properly?
* *Knowing How the Pieces Fit Together:* Do you understand how the software works, and how it might fit into a greater ecosystem?
* *Organization:* Do you approach the problem in a structured manner?
* *Practicality:* Can you actually create reasonable testing plans?

### Testing a Real World Object

#### Step 1: Who will use it? And why?

#### Step 2: What are the use cases?

#### Step 3: What are the bounds of use?

#### Step 4: What are the stress/failure conditions?

#### Step 5: How would you perform the testing?

### Testing a Piece of Software
Testing a piece of software is actually very simialar to testing a real world object. The major difference is that software testing generally places a greater emphasis on the details of performing testing.

Note that software testing has two core aspects to it:

* *Manual vs. Automated Testing*
* *Black Box Testing vs. White Box Tesing:* This distinction refers to the degree of acess we have into the software. In black box testing, we're just given the software as-is and need to test it. With white box testing, we have additional programmatic access to test individual functions. We can also automate some black box testing, although it's certainly much harder.

#### Step 1: Are we doing Black Box Testing or White Box Testing?

#### Step 2: Who will use it? And why?

#### Step 3: What are the use cases?

#### Step 4: What are the bounds of use?

#### Step 5: What are the stress conditions/failure conditions?

#### Step 6: What are the test cases? How would you perform the testing?

### Testing a Function

#### Step 1: Define the test cases
In general, you should think about the following types of test cases:

* *The normal case*
* *The extremes*
* *Nulls and "illegal" input*
* *Strange input*

#### Step 2: Define the expected result
Note: In some cases, you might want to validate additional aspects. For instance, if the `sort` method returns a new sorted copy of the arrya, you should probably validate that the original array has not been touched.

#### Step 3: Write test code

### Troubleshooting Questions

#### Step 1: Understand the Scenario
The first thing you should do is ask questions to understandas as much about the situation as possible.

* How long has the user been experiencing this issue?
* What version of the browser is it? What operating system?
* Does the issue happen consistently, or how often does it happen? When does it happen?
* Is there an error report that launches?

#### Step 2: Break Down the Problem
Now that you understand the details, you want to break down the problem into testable units.

#### Step 3: Create Specific, Manageable Tests
In the real world, you will be dealing with customers, and you can't give them instructions that they can't or won't do.

## Chapter 13: Java

### How to Approach
1. Create an example of the scenario, and ask yourself how things should play out.
2. Ask yourself how other languages would handle this scenario.
3. Consider how you would design this situation if you were the language designer. What would the implications of each choice be?

### Overloading vs. Overriding
Overloading is a term used to describe when two methods have the same name but differ in the type or number of arguments.

Overriding, however, occurs when a method shares the same name and function signature as another method in its super class.

### Collection Framework
Here are some of the most useful items from Java's collection framework:

* `ArrayList`: An ArrayList is a dynamically resizing array, which grows as you insert elements.
* `Vector`: A vector is very similar to an ArrayList, except that is it synchronized.
* `LinkedList`
* `HashMap`

## Chapter 14: Databases

### SQL Syntax and Variations

```
Explicit Join
SELECT CourseName, TeacherName
FROM Courses INNER JOIN Teachers
ON Courses.TeacherID = Teachers.TeacherID

Implicit Join
SELECT CourseName, TeacherName
FROM Courses, Teachers
WHERE Courses.TeacherID =
      Teacher.TeacherID
```

### Denormalized vs. Normalized Databases
Normalized databases are designed to minimize redundancy, while denomalized databases are designed to optimize read time.

In traditional normalized databases, many common queries will require expensive joins. Denomalization is commonly used to create highly scalable systems.

### Small Database Design

#### Step 1: Handle Ambiguity

#### Step 2: Define the Core Objects

#### Step 3: Analyze Relationships

#### Step 4: Investigate Actions
Fill in the details. Walk through the common actions that will be taken and understand how to store and retrieve the relevant data.

### Large Database Design
When designing a large, scalable database, joins are generally very slow. Thus, you must *denomalize* your data. Think carefully about how data will be used - you'll probably need to duplicate the data in multiple tables.

## Chapter 15: Threads and Locks

### Threads in Java
Every thread in Java is created and controlled by a unique object of the `java.lang.Thread` class. When a standalone application is run, a user thread is automatically created to execute the `main()` method. This thread is called the main thread.

In Java, we can implement threads in one of two ways:

* By implementing the `java.lang.Runnable` interface
* By extending the `java.lang.Thread` class

#### Implementing the Runnable Interface
The `Runnable` interface has the following very simple structure:

```
public interface Runnable {
    void run();
}
```

To create and use a thread using this interface, we do the following:

1. Create a class which implements the `Runnable` interface. An object of this class is a `Runnable` object.
2. Create an object of type `Thread` by passing a `Runnable` object as argument to the `Thread` constructor. The `Thread` object now has a `Runnable` object that implements the `run()` method.
3. The `start()` method is invoked on the `Thread` object created in the previous step.

#### Extending the Thread Class
This will almost always mean that we override the run() method, and the subclass may also call the thread constructor explicitly in its constructor.

This will be very similar to the first approach. The difference is that since we are extending the `Thread` class, rather than just implementing an interface, we can call `start()` on the instance of the class itself.

#### Extending the Thread Class vs. Implementing the Runnable Interface
When creating threads, there are two reasons why implementing the `Runnable` interface may be preferable to extending the `Thread` class:

* Java does not support multiple inheritance. Therefore, extending the `Thread` class means that the subclass cannot extend any other class.
* A class might only be interested in being runnable, and therefore, inheritimg the full overhead of the `Thread` class would be excessive.

### Synchronization and Locks
Threads within a given process share the same memory space, which is both a positive and a negative. It enables threads to share data, which can be valuable. However, it also creates the opportunity for issues when two threads modify a resource at the same time. Java provides synchronization in order to control access to shared resources.

The keyword `synchronized` and the `lock` form the basis for implementing synchronized execution of code.

#### Synchronized Methods
We restrict access to shared resources through the use of the `synchronized` keyword. It can be applied to methods and code blocks, and restricts multiple threads from executing the code simultaneously *on the same object*.

```java
public class MyClass extends Thread {
    private String name;
    private MyObject myObj;

    public MyClass(MyObject obj, String n) {
        name = n;
        myObj = obj;
    }

    public void run() {
        myObj.foo(name);
    }
}

public class MyObject {
    public synchronized void foo(String name) {
        try {
            System.out.println("Thread " + name + ".foo(): starting");
            Thread.sleep(3000);
            System.out.println("Thread " + name + ".foo(): ending");
        } catch (InterruptedException exc) {
            System.out.println("Thread " + name + ".foo(): interrupted");
        }
    }
}
```

Can two instances of `MyClass` call `foo` at the same time? It depends. If they have the same instance of `MyObject`, then no. But, if they hold differenct references, then yes.

Static methods synchronize on the `class` lock. Two threads cannoy simultaneously execute synchronized static methods on the same class, even if one is calling `foo` and the other is calling `bar`.

#### Synchronized Blocks
Similarly, a block of code can be synchronized. This operates very similarly to synchronizing a method.

```java
public class MyClass extends Thread {
    ...
    public void run() {
        myObj.foo(name);
    }
}

public class MyObject {
    public void foo(String name) {
        synchronized(this) {
            ...
        }
    }
}
```

Like synchronizing a method, only one thread per instance of `MyObject` can execute the code within the `synchronized` block. That means that, if `thread1` and `thread2` have the same instance of `MyObject`, only one will be allowed to execute the code block at a time.

#### Locks
A lock (or monitor) is used to synchronize access to a shared resource by associating the resource with the lock. A thread gets access to a shared resource by first acquiring the lock associated with the resource. At any given time, at most one thread can hold the lock and, therefore, only one thread can access the shared resource.

A common use case for locks is when a resource is accessed from multiple places, but should only be accessed by one thread *at a time*.

```java
public class LockedATM {
    private Lock lock;
    private int balance = 100;

    public LockedATM() {
        lock = new ReentrantLock();
    }

    public int withdraw(int value) {
        lock.lock();
        int temp = balance;
        try {
            Thread.sleep(100);
            temp = temp - value;
            Thread.sleep(100);
            balance = temp;
        } catch(InterruptedException e) { }
        lock.unlock();
        return temp;
    }

    public int deposit(int value) {
        lock.lock();
        int temp = balance;
        try {
            Thread.sleep(100);
            temp = temp + value;
            Thread.sleep(300);
            balance = temp;
        } catch(InterruptedException e) { }
        lock.unlock();
        return temp;
    }
}
```

### Deadlocks and Deadlock Prevention
A deadlock is a situation where a thread is waiting for an object lock that another thread holds, and this second thread is waiting for an object lock that the first thread holds (or equivalent situation).

In order for a deadlock to occur, you must have all four of the following conditions met:

1. *Mutual Exclusion:* Only one process can access a resource at a given time. (Or, more accurately, there is limited access to a resource. A deadlock could also occur if a resource has limited quantity.)
2. *Hold and Wait:* Processes already holding a resource can request additional resources, without relinquishing their current resources.
3. *No preemption:* One process cannot forcibly remove another process' resource.
4. *Circular Wait:* Two or more processes form a circular chain where each process is waiting on another resource in the chain.

Deadlock prevention entails removing any of the above conditions, but this can be tricky. Most deadlock prevention algorithms focus on avoiding condition #4: circular wait.

## Advanced Topics

### Useful Math

#### Sum of Integers 1 though N
`1 + 2 + ... + n = n(n+1) / 2`

#### Sum of Powers of 2
`2^0 + 2^1 + 2^2 + ... + 2^n = 2^(n+1) - 1`

**Take Away:** The sum of a sequence of powers of two is roughly equal to the *next* value in the sequence.

#### Bases of Logs
What is the relationship between `log_b(k)` and `log_x(k)`?

`log_b(k) = log_x(k)/log_x(b)`

**Takeaway:** Logs of different bases are only off by a constant factor. For this reason, we largely ignore what the base of a log is within a big O expression. It doesn't matter since we drop constants anyway.

#### Permutations
How many ways are there of rearranging a string of n unique characters? The total number of strings is `n!`.

What if you were forming a k-length string (with all unique characters) from n total unique characters? `n! / (n - k)!`.

#### Combinations
Suppose you have a set of n distinct characters. How many ways are there of selecting k characters into a new set (where order doesn't matter)? That is, how many k-sized subsets are there out of n distinct elements? `n-choose-k = n! / (k! (n - k)!)`.

#### Proof by Induction
Task: Prove statement `P(k)` is true for all `k >= b`.

* Base Case: Prove the statement to be true for `P(b)`.
* Assumption: Assume the statement is true for `P(n)`.
* Inductive Step: Prove that *if* the statement is true for `P(n)`, then it's true for `P(n+1)`.

### Topological Sort
A topological sort of a directed graph is a way of ordering the list of nodes such that if `(a, b)` is an edge in the graph then `a` will appear before `b` in the list. If a graph has a cycles or is not directed, then there is no topological sort.

We can construct a topological sort with the following approach:
1. Identify all nodes with no incoming edges and add those nodes to our topological sort.
2. When we do the above, remove each node's outbound edges from the graph.
3. Repeat the above, adding nodes with no incoming edges and removing their outbound edges. When all the nodes have been added to the topological sort, then we are done.

More formally, the algorithm is this:
1. Create a queue `order`, which will eventually store the valid topological sort. It is currently empty.
2. Create a queue `processNext`. This queue will store the next nodes to process.
3. Count the number of incoming edges of each node and set a class variable `node.inbound`. Nodes typically only store their outgoing edges. However, you can count the inbound edges by walking through each node `n` and, for each of its outgoing edges `(n, x)`, incrementing `x.inbound`.
4. Walk through the nodes again and add to `processNext` any node where `x.inbound == 0`.
5. While `processNext` is not empty, do the following:
    * Remove first node `n` from `processNext`.
    * For each edge `(n, x)`, decrement `x.inbound`. If `x.inbound == 0`, append `x` to `processNext`.
    * Append `n` to `order`.
6. If `order` contains all the nodes, then it has succeeded. Otherwise, the topological sort has failed due to a cycle.

### Dijkstra's Algorithm
Dijkstra's algorithm is a way to find the shortest path between two points in a weighted directed graph (which might have cycles). It finds the minimum weight path from a start node s to *every* node on the graph. All edges must have positive values.

It picks the unvisited vertex with the lowest distance, calculates the distance through it to each unvisited neighbor, and updates the neighbor's distance if smaller.

*Priority Queue and Runtime*

* If you implemented the priority queue with an array, the total runtime would be `O(v^2)` which is better for dense graphs.
* If you implemented the prioirty queue with a min heap, the total runtime would be `O((v + e) log v)` which is better for sparse graphs.

### Hash Table Collision Resolution

#### Chaining with Linked Lists
With this approach (which is the most common), the hash table's array maps to a linked list of items. As long as the number of collisions is farily small, this will be quite efficient. In the worst case, lookup is `O(n)`, where `n` is the number of elements in the hash table.

#### Chaining with Binary Search Trees
Rather than storing collisions in a linked list, we could store collisions in a binary search tree. This will bring the worst-case runtime to `O(log n)`. In practice, we would rarely take this approach unless we expected an extremely nonuniform distribution.

#### Open Addressing with Linear Probing
In this approach, when a collision occurs (there is already an item stored at the designated index), we just move onto the next index in the array until we find an open spot. (Or, sometime, some other fixed distance, like the index + 5.)

If the number of collisions is low, this is a very fast and space-efficient solution.

One obvious drawback of this is that the total number of entries in the hash table is limited by the size of the array. This is not the case with chaining.

There's another issue here. Consider a hash table with an underlying array of size 100 where indexes 20 through 29 are filled (and nothing else). What are the odds of the next insertion going to index 30? The odds are 10% because an item mapped to any index between 20 and 30 will wind up at index 30. This causes an issue called *clustering*.

#### Quadratic Probing and Double Hashing
The distance between probes does not need to be linear. You could, for example, increase the probe distance quadratically. Or, you could use a second hash function to determine the probe distance.

### Rabin-Karp Substring Search
The brute force way to search for a substring `S` in a larger string `B` takes `O(s(b-s))`. We do this by searching through the first `b - s + 1` characters in `B` and, for each, checking if the next `s` characters match `S`.

The Rabin-Karp algorithm optimizes this with a little trick: if two strings are the same, they must have the same hash value. (The converse, however, is not true.)

Therefore, if we efficiently precompute a hash value for each sequence of `s` characters within `B`, we can find the locations of `S` in `O(b)` time. We then just need to validate that those locations really do match `S`.

In pratice, we would use a *rolling hash function*, such as the Rabin fingerprint. This essentially treats a string like doe as a base 128 (or however many characters are in our alphabet) number.

`hash('doe') = code('d') * 128^2 + code('o') * 128^1 + code('e') * 128^0`

This hash function will allow us to remove the `d`, shift the `o` and `e`, and then add in the space.
    
`hash('oe ') = (hash('doe') - code('d') * 128^2) * 128 + code(' ')`

This will considerably cut down on the number of false matches. Using a good hash function like this will give us expected time complexity of `O(s + b)`, although the worst case is `O(sb)`.

### AVL Trees
An AVL tree is one of two common ways to implement tree balancing. We will only discuss insertions here, but you can look up deletions separately is you're interested.

#### Properties
An AVL tree stores in each node the height of the subtrees rooted at this node. Then, for any node, we can check if it is height balanced: that the height of the left subtree and the height of the right subtree differ by no more than one.

```
balance(n) = n.left.height - n.right.height
-1 <= balance(n) <= 1
```

#### Inserts
When you insert a node, the balance of some nodes might change to -2 or 2. Therefore, when we "unwind" the recursive stack, we check and fix the balance at each node. We do this through a series of rotations.

Rotations can be either left or right rotations. The right rotation is an inverse of the left rotation.

#### Case 1: Balance is 2.
In this case, the left's height is two bigger than the right's height. If the left side is larger, the left subtree's extra nodes must be hanging to the left (as in LEFT LEFT SHAPE) or hanging to the right (as in LEFT RIGHT SHAPE). If it looks like the LEFT RIGHT SHAPE, transform it with a left rotation into a LEFT LEFT SHAPE then into BALANCED with a right rotation. If it looks like the LEFT LEFT SHAPE already, just transform it into BALANCED with a right rotation.

#### Case 2: Balance is -2.
This case is the mirror image of the prior case.

We recurse up the tree, fixing any imbalances. If we ever achieve a balance of 0 on a subtree, then we know that we have completed all the balances. This portion of the tree will not cause another, higher subtree to have a balance of -2 or 2.

### Red-Black Trees
Red-black trees (a type of self-balancing binary search tree) do not ensure quite as strict balancing, but the balancing is still good enough to ensure `O(log N)` insertions, deletions, and retrievals. They require a bit less memory and can rebalance faster (which means faster insertions and removals), so they are often use in situations where the tree will be modified frequently.

Red-black trees operate by enforcing a quasi-alternating red and black coloring (under certain rules, described below) and then requiring every path from a node to its leaves to have the same number of black nodes. Doing so leads to a reasonably balance tree.

#### Properties
1. Every node is either red or black.
2. The root is black.
3. The leaves, which are NULL nodes, are considered black.
4. Every red node must have two black children. That is, a red node cannot have red children (although a black node can have black children).
5. Every path from a node to its leaves must have the same number of black children.

#### Why It Balances
Consider two paths from a node (say, the root) to its leaves. The paths must have the same number of black nodes (property #5), so let's assume that their red node counts are as different as possible: one path contains the minimum number of red nodes and the other contains the maximum number.
* Path 1 (Min Red): The minimum number of red nodes is zero. Therefore, path 1 has b nodes total.
* Path 2 (Max Red): The maximum number of red ndoes is b, since red nodes must have black children and there are b black nodes. Therefore, path 2 has 2b nodes total.

Therefore, even in the most extreme case, the lengths of the paths cannot differ by more than a factor of two. That's good enough the ensure an `O(log N)` find and insert runtime. (We'll only discuss insertion here, but you can look up deletion on your own.)

#### Insertion
Inserting a new node into a red-black tree starts off with a typical binary search tree insertion.
* New nodes are inserted at a leaf, which means that they replace a black node.
* New nodes are always colored red and are given two black leaf (NULL) nodes.

Once we've done that, we fix any resulting red-black property violations. We have two possible violations:
* Red violations: A red node has a red child (or the root is red).
* Black violations: One path has more blacks than another path.

The node inserted is red. We didn't change the number of black nodes on any path to a leaf, so we know that we won't have a black violation. However, we might have a red violation.

In the special case where the root is red, we can always just turn it black to satisfy property 2, without violating the other constraints.

Otherwise, if there's a red violation, then this means that we have a red node under another red node. Oops!

Let's call N the current node. P is N's parent. G is N's grandparent. U is N's uncle and P's sibling. We know that:
* N is red and P is red, since we have a red violation.
* G is definitly black, since we didn't *previously* have a red violation.

The unknown parts are:
* U could be either red or black.
* U could be either a left or right child.
* N could be either a left or right child.

By simple combinatorics, that's eight cases to consider. Fortunately, some of these cases will be equivalent.

#### Case 1: U is red:
It doesn't matter whether U is a left or right child, nor whether P is a left or right child. We can merge four of our eight cases into one.

If U is red, we can just toggle the colors of P, U, and G. Flip  G from black to red. Flip P and U from red to black. We haven't changed the number of black nodes in any path.

However, by making G red, we might have created a red violation with G's parent. If so, we recursively apply the full logic to handle a red violation, where this G becomes the new N.

Note that in the general recursive case, N, P, and U may also have subtrees in place of each black NULL leaf. In case 1, these subtrees stay attached to the same parents, as the tree structure remains unchanged.

#### Case 2: U is black:
We'll need to consider the configurations (left vs. right child) of N and U. In each case, our goal is to fix up the red violation (red on top of red) without:
* Messing up the ordering of the binary search tree
* Introducing a black violation (more black nodes on on path than another).

If we can do this, we're good. In esch of the cases below, the red violation is fixed with rotations that maintain the node ordering.

*Case A: N and P are both left children.*

We resolve the red violation with a right rotation of N, P, and G and the associated recoloring: P goes from red to black, and G goes from black to red. In all, we have: P (black), N and G (red), N and G's children (black).

*Case B: P is a left child, and N is a right child.*

We resolve the red violation with a left rotation followed by a right rotation, changing N from red to black and G from black to red.

*Case C: N and P are both right children.*

This is a mirror image of case A.

*Case D: N is a left child, and P is a right child.*

This is a mirror image of case B.

### MapReduce
MapReduce is used widely in system design to process large amounts of data in parallel. As its name suggests, a MapReduce program requires you to write a Map step and a Reduce step. The rest is handled by the system.

1. The system splits up the data across different machines.
2. Each machine starts running the user-provided Map program.
3. The Map program takes some data and emits a `<key, value>` pair.
4. The system-provided `Shuffle` process reorganizes the data so that all `<key, value>` pairs associated with a given key go to the same machine, to be processed by `Reduce`.
5. The user-provided `Reduce` program takes a key and a set of associated values and "reduces" then in some way, emitting a new key and value. The results of this might be fed back into the Reduce program for more reducing.

*In many cases*, it's useful to think about what the `Reduce` step should do first, and then design the `Map` step around that. What data does `Reduce` need to have to do its job?

### Additional Studying
* **Bellman-Ford Algorithm:** Finds the shortest paths from a single node in a weighted directed graph with positive and negative edges.
* **Floyd-Warshall Algorithm:** Finds the shortest paths in a weighted graph with positive or negative weight edges (but no negative weight cycles).
* **Minimum Spanning Tree:** In a weighted, connected, undirected graph, a spanning tree is a tree that connects all the vertices. The minimum spanning tree is the spanning tree with minimum weight. There are various algorithms to do this.
* **B-Trees:** A self-balancing search tree (not a binary search tree) that is commonly used on disks or other storage devices. It is similar to a red-black tree, but uses fewer I/O operations.
* **A\*:** Find the least-cost path between a source node and a goal node (or one of several goal nodes). It extends Dijkstra's algorithm and achieves better performance by using heuristics.
* **Interval Trees:** An extension of a balances binary search tree, but storing intervals (low -> high ranges) instead of simple values.
* **Graph coloring:** A way of coloring the nodes in a graph such that no two adjacent vertices have the same color. There are various algorithms to do things like determine if a graph can be colored with only k colors.
* **P, NP, and NP-Complete**: P, NP, and NP-complete refer to classes of problems. P problems are problems that can be quickly solved (where "quickly" means polynomial time). NP problems are those where, given a solution, the solution can be quickly verified. NP-Complete problems are a subset of NP problems that can all be reduced to each other (that is, if you found a solution to on problem, you could tweak the solution to solve other problems in the set in polynomial time).
* **Combinatorics and Probability:** There are various things you can learn about here, such as random variables, expected value, and n-choose-k.
* **Bipartite Graph:** A bipartite graph is a graph where you can divide its nodes into two sets such that every edge stretches across the two sets. There is an algorithm to check if a graph is a bipartite graph. Note that a bipartite graph is equivalent to a graph that can be colored with two colors.
* **Regular Expressions:** You should know that regular expressions exist and what they can be used for (roughly). You can also learn about how an algorithm to match regular expressions would work. Some of the basic syntax behind regular expressions could be useful as well.
