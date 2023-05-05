package src;

import java.util.concurrent.Semaphore;

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
    int count = 0;
    public int getCount() { //getter for count
        return count;
    }

    public void setCount(int count) { //setter for count
        this.count = count;
    }
    //private final Semaphore insertSem;

    // Constructor for BST =>initial empty tree
    BST_class(){
        root = null;
        count = 0;
        //insertSem = new Semaphore(1);

    }
    public int size() {
        return count; //Returns the count (size) of the tree
    }

    //delete a node from BST
    synchronized void deleteKey(int key) {
        count--;
        root = delete_Recursive(root, key);
    }

    //recursive delete function
    synchronized Node delete_Recursive(Node root, int key)  {
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
        root = insert_Recursive(root, key);
        count++;
        //root.nodeSem.release();

    }

    //recursive insert function
    Node insert_Recursive(Node root, int key) throws InterruptedException {
        //tree is empty
        //add case where if the node has a parent, release the parent semaphore

        if (root == null) {
            root = new Node(key);
            //root.nodeSem.release();
            return root;
        }
        root.nodeSem.acquire();

//        while (root.left != null){
//            root.nodeSem.release();
//            root = root.left;
//        }
        //traverse the tree
        if (key < root.key) {     //insert in the left subtree
            root.left = insert_Recursive(root.left, key);
            root.nodeSem.release();
        }
        else if (key > root.key) {
            //insert in the right subtree
            root.right = insert_Recursive(root.right, key);
            root.nodeSem.release();
        }
        // return pointer
        //root.nodeSem.release();
        return root;
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

    public boolean invariantChecker() {
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


}
