package src;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * This is my BST and node classes for the concurrent BST data structure
 * I received some logic for this lab from: https://www.geeksforgeeks.org/construct-bst-given-level-order-traversal/
 */

class BST_class extends Thread {
    //final static int debug_keys_above = 0; /// set bigger than any test key, to turn off
    //node class that defines BST node

    /**
     * This is a node class. In this every time we want to insert something into the tree for example, we create  node for it. Additionally for concurrency
     * each node will have its own semaphore
     */
    class Node {
        int key; //value of each node
        Node left, right; //can access the left and right nodes

        private final Semaphore nodeSem; //semaphore that each node will have

        /**
         * Constructor for the node that has the parameters I discussed above
         * @param data the value of the node which will be an integer.
         */
        public Node(int data){
            key = data;
            left = right = null;
            nodeSem = new Semaphore(1);
        }
    }
    // BST root node
    Node root; //first node in the BST
    int count = 0;

    //private final Semaphore insertSem;

    // Constructor for BST =>initial empty tree

    /**
     * Constructor that initializes the properties of a BST
     */
    BST_class(){
        root = null;
        count = 0;
        //insertSem = new Semaphore(1);
    }

    private final Object waitingRemove = new Object();
    private final Object waitingInsert = new Object(); //These three are objects that are used to synchronize when we should add, delete or use the invariant checker
    private final Object waitingChecker = new Object();
    private final Object waitingSearch = new Object();

    private int insertCounter = 0; //counter for insert like I did in apes and ladders. Before inserting and after inserting to make sure the process is done
    private int matchCountInsert = 0;
    private int removeCounter = 0; //counter for remove like I did in apes and ladders. Before removing and after removing to make sure the process is done
    private int matchCountRemove = 0;
    private int checkerCounter = 0; //counter for invariant checker like I did in apes and ladders. Before checking and after checking to make sure the process is done
    private int matchCheckerCounter = 0;
    private int searchCounter = 0;
    private int matchSearchCounter = 0;
    private boolean isInserting = false; //this is used to ensure inserting and removing take turns
    public int rootCount = 0;

    /**
     * Size functions that returns the size of the tree as an int
     * @return the size of the tree as an int
     */
    public int size() {
        return count; //Returns the count (size) of the tree
    }

    //delete a node from BST

    /**
     * This is a delete function that deletes a given node from the BST if it exists
     * @param key the value of the node
     * @throws InterruptedException exception bc of concurrency
     */
    public void deleteKey(int key) throws InterruptedException {
        synchronized (waitingRemove) {
            removeCounter++; //start the first remove counter as it has entered the function
            waitingRemove.wait();
        }
        count--;
        root = delete_Recursive(root, key); //do the deletion with the helper function
        matchCountRemove++; //Increment the second counter

    }

    //recursive delete function

    /**
     * This is the helper function for our delete function
     * @param root the root of the bst
     * @param key the value of the node
     * @return the node we are deleting
     */
    private Node delete_Recursive(Node root, int key)  {
        //tree is empty
        if (root == null) { //base case
            return root;
        }

        //traverse the tree
        if (key < root.key)     //traverse left subtree
            root.left = delete_Recursive(root.left, key); //root now turns into root.left for the next cycle of recursion
        else if (key > root.key)  //traverse right subtree
            root.right = delete_Recursive(root.right, key); //root now turns into root.right for the next cycle of recursion
        else  {
            // node contains only one child
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            // node has two children;
            //get inorder successor (min value in the right subtree)
            root.key = minValue(root.right); //finds the min value using a function we created

            // Delete the inorder successor
            root.right = delete_Recursive(root.right, root.key); //this deletes the in order successor
        }
        return root; //return the delted node
    }

    /**
     * Find the minimum value of the tree
     * @param root the root of the bst
     * @return the node with the smallest value
     */
    int minValue(Node root)  {
        //initially minval = root
        int minval = root.key;
        //find minval
        while (root.left != null)  {
            minval = root.left.key; //keep going left until you find the last node in the left subtree
            root = root.left;
        }
        return minval;
    }
    // insert a node in BST

    /**
     * This is a concurrent insert function that uses a helper function
     * @param key the value of the node
     * @throws InterruptedException exception for concurrency
     */
    void insert(int key) throws InterruptedException {
//        if (key > debug_keys_above) System.out.println("Debug: inserting key " + key);
        synchronized (waitingInsert) {
            insertCounter++; //increment for insert counter as it has entered the method
            waitingInsert.wait();
        }
        synchronized (this) { //base case if there is no nodes in the tree. Must be synchronized to prevent a data race
            if (root == null) {
                root = new Node(key); //create a new node if there is nothing in the tree
                count++;
                matchCountInsert++; //increment the second counter
                return; //am i allowed to have empty returns
            }
        }
        count++;
//        if (key > debug_keys_above) System.out.println("Debug: recursive inserting key from root " + key);
        insert_Recursive(root, key); //recursive call for inserting
    }

    //recursive insert function

    /**
     * Helper function for inserting
     * @param curr the current node we are on in the bst
     * @param key the value of the node as an int
     * @throws InterruptedException exception used for concurrency
     */
    private void insert_Recursive(Node curr, int key) throws InterruptedException {
        //tree is empty
        //add case where if the node has a parent, release the parent semaphore

        if (curr == null) { //a base case that it should never reach
            synchronized (waitingInsert) {
                Thread.sleep(500);
                matchCountInsert++;
                System.out.println("Shouldn't get here");
                waitingInsert.wait();
            }
            return;
        }

        curr.nodeSem.acquire(); //acquire the semaphore of the current node

//        if (key > debug_keys_above) System.out.println("Debug: recursive inserting, locking " + curr.key + " as I work on key " + key);
        //traverse the tree
        if (key < curr.key) {
            if(curr.left == null){
                Node newNodeLeft = new Node(key); //if we need to go left and there are no nodes left, create a new node
                newNodeLeft.nodeSem.acquire(); //acquire this node as well
                curr.left = newNodeLeft;
                curr.nodeSem.release();
                curr.left.nodeSem.release(); //once we know everything is done and changed, we can release them
                Thread.sleep(500);
                synchronized (waitingInsert) {
                    matchCountInsert++; //increment the second counter
                    waitingInsert.wait();
                }
            } else {
                curr.nodeSem.release(); //release the semaphore of the current node
//                if (key > debug_keys_above) System.out.println("Debug: recursive inserting, going left  after unlocking " + curr.key + " as I work on key " + key);
                insert_Recursive(curr.left, key); //recursive call
                count++;
            }
        }
        if (key == curr.key){
            curr.nodeSem.release(); //if they are equal, we don't want to add it
            synchronized (waitingInsert) {
                matchCountInsert++; //still, increment the second counter
                waitingInsert.wait();
            }

        }
        else if (key > curr.key) { //going to the right subtree
            if(curr.right == null) {
                Node newNodeRight = new Node(key);
                newNodeRight.nodeSem.acquire();
                curr.right = newNodeRight;
                curr.nodeSem.release(); //same exact logic as when we are going left
                curr.right.nodeSem.release();
                Thread.sleep(500);
                synchronized (waitingInsert) {
                    matchCountInsert++;
                    waitingInsert.wait();
                }
            } else {
                curr.nodeSem.release();
//                if (key > debug_keys_above) System.out.println("Debug: recursive inserting, going right after unlocking " + curr.key + " as I work on key " + key);
                insert_Recursive(curr.right, key);
            }
        }


//        curr.nodeSem.release();
        // return pointer
    }


    // method for inorder traversal of BST

    /**
     * printing the inorder bst
     */
    void inorder() {
        inorder_Recursive(root);
    }

    // recursively traverse the BST

    /**
     * How we print the inorder bst, go left, root then right
     * @param root the root of the bst
     */
    void inorder_Recursive(Node root) {
        if (root != null) {
            inorder_Recursive(root.left);
            System.out.print(root.key + " ");
            inorder_Recursive(root.right);
        }
    }

    /**
     * Search function that sees if an element is in the tree
     * @param key int value of the node
     * @return a boolean for if the node is in the tree or not
     */
    boolean search(int key) throws InterruptedException {
        synchronized (waitingSearch){
            searchCounter++; //increment search counter
            waitingSearch.wait();
        }
        if (search_Recursive(root, key)!= null){
            matchSearchCounter++; //once the process is done with, increment the second counter
            return true; //if its in the tree
        } else {
            matchSearchCounter++; //same logic for the false logic
            return false;
        }
    }

    //recursive insert function

    /**
     * Helper function for the search function
     * @param root root of the bst
     * @param key value of the node
     * @return if the node is in the bst or not
     */
    Node search_Recursive(Node root, int key)  {
        // Base Cases: root is null or key is present at root
        if (root==null || root.key==key)
            return root;
        // val is greater than root's key
        if (root.key > key)
            return search_Recursive(root.left, key);
        // val is less than root's key
        return search_Recursive(root.right, key);
    }

    /**
     * Checking to see if it is a valid BST
     * @return a boolean statement. True if its valid, false if it isn't
     * @throws InterruptedException exception for concurrency
     */
    public boolean invariantChecker() throws InterruptedException {
//        synchronized (waitingChecker){
//            checkerCounter++;
//            waitingChecker.wait();
//        }
        return validate(root, null, null); //return the helper function
    }

    /**
     * Helper function for the invariant checker
     * @param root root of the bst
     * @param low check previous element is less than the current one
     * @param high check previous element is more than the current one
     * @return true or false if it is a valid tree or not
     */
    public boolean validate(Node root, Integer low, Integer high){
        if (root == null) { //check is complete with nothing wrong
            synchronized (waitingChecker){ //first checker
                checkerCounter++;
            }
            synchronized (waitingChecker) {
                matchCheckerCounter++; //second checker
            }
            return true;
        }
        if((low != null && root.key <= low) || (high != null && root.key >= high)) { //something is incorrect
            synchronized (waitingChecker){
                checkerCounter++;//first checker
            }
            synchronized (waitingChecker) {
                matchCheckerCounter++; //second checker
            }
            return false;
        }
        return validate(root.right, root.key, high) && validate(root.left, low, root.key); //keep recurring through the tree
    }

    /**
     * This is a test to ensure the invariant checker is working as we set the root to a very large number
     */
    public void falseBST(){
        root.key = 150000;
    }

    /**
     * This function works with our timer function to let inserts happen and then when the timer goes off, removes and the invariant checker can go
     * @throws InterruptedException exception for concurrency
     */
    private void changeInsert() throws InterruptedException {
        isInserting = !isInserting; //changes the "ladder" which lets inserts or removes go

        if (isInserting) {
            synchronized (waitingInsert) {
                waitingInsert.notifyAll();
            }
            int goingIn = insertCounter;
            while (goingIn != matchCountInsert) { //make sure the removes don't go by ensuring inserts ended by using the double counter
                Thread.sleep(400);
                System.out.println(goingIn + " == " + matchCountInsert);
            }
        } else { //ladder flip
            synchronized (waitingRemove) { //same logic as the inserts
                waitingRemove.notifyAll();
            }
            int goingInRemove = removeCounter;
            while (goingInRemove != matchCountRemove) {
                Thread.sleep(400);
                System.out.println("Waiting for all of the removes to finish");
            }
        }

        synchronized (waitingChecker) {
            waitingChecker.notifyAll();
        }
        int goingInInvar = checkerCounter;
        while (goingInInvar != matchCheckerCounter) { //same logic as above
            Thread.sleep(400);
            System.out.println("Waiting for invariant checker to finish checking");
        }

        synchronized (waitingSearch) {
            waitingSearch.notifyAll();
        }
        int goingInSearch = searchCounter; //same logic as above
        while(goingInSearch != matchSearchCounter) {
            Thread.sleep(400);
            System.out.println("Waiting for the search to finish searching");
        }
    }

    /**
     * This is a timer that allows inserts and removes/checker to take turns
     */
    public void timing() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                try {
                    changeInsert(); //calls the previous function as that is where the "ladder" keeps changing
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        timer.schedule(task, 30, 1); // the schedule to call the method
    }




}
