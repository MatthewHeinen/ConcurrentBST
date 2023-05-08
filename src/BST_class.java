package src;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

class BST_class extends Thread {
    final static int debug_keys_above = 0; /// set bigger than any test key, to turn off
    //node class that defines BST node
    class Node {
        int key;
        Node left, right;

        private final Semaphore nodeSem;

        public Node(int data){
            key = data;
            left = right = null;
            nodeSem = new Semaphore(1);
        }
    }
    // BST root node
    Node root;
    AtomicInteger count = new AtomicInteger(0);

    //private final Semaphore insertSem;

    // Constructor for BST =>initial empty tree
    BST_class(){
        root = null;
        count = new AtomicInteger(0);
        //insertSem = new Semaphore(1);
    }

    private final Object waitingRemove = new Object();
    private final Object waitingInsert = new Object();
    private final Object waitingChecker = new Object();

    private int insertCounter = 0;
    private int matchCountInsert = 0;
    private int removeCounter = 0;
    private int matchCountRemove = 0;
    private int checkerCounter = 0;
    private int matchCheckerCounter = 0;
    private boolean isInserting = false;
    public int rootCount = 0;
    public int size() {
        return count.get(); //Returns the count (size) of the tree
    }

    //delete a node from BST
    public void deleteKey(int key) throws InterruptedException {
        synchronized (waitingRemove) {
            removeCounter++;
            waitingRemove.wait();
        }
        count.decrementAndGet();
        root = delete_Recursive(root, key);
        matchCountRemove++;

    }

    //recursive delete function
    private Node delete_Recursive(Node root, int key)  {
        //tree is empty
        if (root == null)  return root;

        //traverse the tree
        if (key < root.key)     //traverse left subtree
            root.left = delete_Recursive(root.left, key);
        else if (key > root.key)  //traverse right subtree
            root.right = delete_Recursive(root.right, key);
        else  {
            // node contains only one child
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            // node has two children;
            //get inorder successor (min value in the right subtree)
            root.key = minValue(root.right);

            // Delete the inorder successor
            root.right = delete_Recursive(root.right, root.key);
        }
        return root;
    }

    int minValue(Node root)  {
        //initially minval = root
        int minval = root.key;
        //find minval
        while (root.left != null)  {
            minval = root.left.key;
            root = root.left;
        }
        return minval;
    }
    // insert a node in BST
    void insert(int key) throws InterruptedException {
        if (key > debug_keys_above) System.out.println("Debug: inserting key " + key);
        synchronized (waitingInsert) {
            insertCounter++;
            waitingInsert.wait();
        }
        synchronized (this) {
            if (root == null) {
                root = new Node(key);
                count.incrementAndGet();
                matchCountInsert++;
                return; //am i allowed to have empty returns
            }
        }
        count.incrementAndGet();
        if (key > debug_keys_above) System.out.println("Debug: recursive inserting key from root " + key);
        insert_Recursive(root, key);
    }

    //recursive insert function
    private void insert_Recursive(Node curr, int key) throws InterruptedException {
        //tree is empty
        //add case where if the node has a parent, release the parent semaphore

        if (curr == null) {
            synchronized (waitingInsert) {
                matchCountInsert++;
                System.out.println("Shouldn't get here");
                waitingInsert.wait();
            }
            return;
        }

        curr.nodeSem.acquire();

        if (key > debug_keys_above) System.out.println("Debug: recursive inserting, locking " + curr.key + " as I work on key " + key);
        //traverse the tree
        if (key < curr.key) {
            if(curr.left == null){
                Node newNodeLeft = new Node(key);
                newNodeLeft.nodeSem.acquire();
                curr.left = newNodeLeft;
                curr.nodeSem.release();
                curr.left.nodeSem.release();
                synchronized (waitingInsert) {
                    matchCountInsert++;
                    waitingInsert.wait();
                }
            } else {
                curr.nodeSem.release();
                if (key > debug_keys_above) System.out.println("Debug: recursive inserting, going left  after unlocking " + curr.key + " as I work on key " + key);
                insert_Recursive(curr.left, key);
            }
        }
        else if (key > curr.key) {
            if(curr.right == null) {
                Node newNodeRight = new Node(key);
                newNodeRight.nodeSem.acquire();
                curr.right = newNodeRight;
                curr.nodeSem.release();
                curr.right.nodeSem.release();
                synchronized (waitingInsert) {
                    matchCountInsert++;
                    waitingInsert.wait();
                }
            } else {
                curr.nodeSem.release();
                if (key > debug_keys_above) System.out.println("Debug: recursive inserting, going right after unlocking " + curr.key + " as I work on key " + key);
                insert_Recursive(curr.right, key);
            }
        }
        else if (key == curr.key){
            curr.nodeSem.release();
        }

//        curr.nodeSem.release();
        // return pointer
    }


    // method for inorder traversal of BST
    void inorder() {
        inorder_Recursive(root);
    }

    // recursively traverse the BST
    void inorder_Recursive(Node root) {
        if (root != null) {
            inorder_Recursive(root.left);
            System.out.print(root.key + " ");
            inorder_Recursive(root.right);
        }
    }

    boolean search(int key)  {
        root = search_Recursive(root, key);
        if (root!= null)
            return true;
        else
            return false;
    }

    //recursive insert function
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

    public boolean invariantChecker() throws InterruptedException {
        synchronized (waitingChecker){
            checkerCounter++;
            waitingChecker.wait();
        }
        return validate(root, null, null);
    }

    public boolean validate(Node root, Integer low, Integer high){
        if (root == null) {
            synchronized (waitingChecker) {
                matchCheckerCounter++;
            }
            return true;
        }
        if((low != null && root.key <= low) || (high != null && root.key >= high)) {
            synchronized (waitingChecker) {
                matchCheckerCounter++;
            }
            return false;
        }
        return validate(root.right, root.key, high) && validate(root.left, low, root.key);
    }

    public void falseBST(){
        root.key = 150000;
    }

    private void changeInsert() throws InterruptedException {
        isInserting = !isInserting;

        if (isInserting) {
            synchronized (waitingInsert) {
                waitingInsert.notifyAll();
            }
            int goingIn = insertCounter;
            while (goingIn != matchCountInsert) {
                Thread.sleep(400);
                System.out.println(goingIn + " == " + matchCountInsert);
            }
        } else {
            synchronized (waitingRemove) {
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
        while (goingInInvar != matchCheckerCounter) {
            Thread.sleep(400);
            System.out.println("Waiting for invariant checker to finish checking");
        }
    }

    public void timing() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                try {
                    changeInsert();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        timer.schedule(task, 30, 1); // the schedule to call the method
    }




}
