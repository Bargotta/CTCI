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
