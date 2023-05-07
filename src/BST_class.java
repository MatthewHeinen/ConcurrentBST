package src;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

class BST_class extends Thread {
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
    private final AtomicBoolean isInserting = new AtomicBoolean(false);
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
        synchronized (waitingInsert) {
            insertCounter++;
            waitingInsert.wait();
        }
        if (root == null) {
            root = new Node(key);
            count.incrementAndGet();
            return; //am i allowed to have empty returns
        }
        count.incrementAndGet();
        insert_Recursive(root, key);
        matchCountInsert++;

    }

    //recursive insert function
    private void insert_Recursive(Node curr, int key) throws InterruptedException {
        //tree is empty
        //add case where if the node has a parent, release the parent semaphore

        if (curr == null) {
            return;
        }

        curr.nodeSem.acquire();
        //traverse the tree
        if (key < curr.key) {
            if(curr.left == null){
                Node newNode = new Node(key);
                newNode.nodeSem.acquire();
                curr.left = newNode;
                curr.nodeSem.release();
                curr.left.nodeSem.release();
//                root.left = curr.left;
            } else {
                curr.nodeSem.release();
                insert_Recursive(curr.left, key);

            }
        }
        else if (key > curr.key) {
            if(curr.right == null) {
                Node newNode = new Node(key);
                newNode.nodeSem.acquire();
                curr.right = newNode;
                curr.nodeSem.release();
                curr.right.nodeSem.release();
//                root.right = curr.right;
            } else {
                curr.nodeSem.release();
                insert_Recursive(curr.right, key);

            }
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
        matchCheckerCounter++;
        return validate(root, null, null);
    }

    public boolean validate(Node root, Integer low, Integer high){
        if (root == null) {
            return true;
        }
        if((low != null && root.key <= low) || (high != null && root.key >= high)) {
            return false;
        }
        return validate(root.right, root.key, high) && validate(root.left, low, root.key);
    }

    public void falseBST(){
        root.key = 150000;
    }

    private void changeInsert() throws InterruptedException {
        isInserting.set(!isInserting.get());

        if (isInserting.get()) {
            synchronized (waitingInsert) {
                waitingInsert.notifyAll();
            }
            while (insertCounter != matchCountInsert) {
                Thread.sleep(400);
                System.out.println("Waiting for all of the inserts to finish");
            }
        } else {
            synchronized (waitingRemove) {
                waitingRemove.notifyAll();
            }
            while (removeCounter != matchCountRemove) {
                Thread.sleep(400);
                System.out.println("Waiting for all of the removes to finish");
            }
        }

        synchronized (waitingChecker) {
            waitingChecker.notifyAll();
        }
        while (checkerCounter != matchCheckerCounter) {
            Thread.sleep(400);
            System.out.println("Waiting for invariant checker to finish checking");
        }
    }

    public void timing() {
        Timer timer = new Timer(); // timer
        TimerTask task = new TimerTask() { // creating a timer task
            @Override
            public void run() {
                try {
                    changeInsert(); // a method we are calling on
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        timer.schedule(task, 30, 1); // the schedule to call the method
    }




}
