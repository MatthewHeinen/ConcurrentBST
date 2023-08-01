# ConcurrentBST
This project used Java Concurrency in helping build a concurrent BST. 
I was able to build a concurrent insert function by recursively ensuring only two nodes were able to be accessed at one time using monitors
Also using monitors, I was able to ensure that no other functions such as removing nodes would interfere and create deadlock or give incorrect results
