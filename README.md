# CTCI
Interview Prep

### Chapter 1: Arrays and Strings

#### Hash Tables:
A hash table is a data structure that maps keys to values for highly efficient lookup through the use of a hash code function. Note that two different keys could have the same hash code, as there may be an infinite number of keys and a finite number of ints. If the number of collisions is very high, the worst case runtime is `O(N)`; generally, lookup time is `O(1)`.

#### ArrayList & Resizable Arrays
ArrayLists (and resizable arrays) have amortized insertion time of `O(1)`.

#### StringBuilder
Naively concatenating n strings runs in `O(xn^2)`, assuming that all strings have a length of `x`. On each concatenation, a new copy of the string is created. `Stringbuilder` simply creates a resizable array of all the strings, copying them back to a string only when necessary.

### Chapter 2: Linked Lists 