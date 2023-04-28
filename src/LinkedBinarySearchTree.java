package src;

/**
 * This is the class for creating our linked binary search tree methods such as insert as well as all the toString functions that will be printed to the user
 * @param <E> is a generic. Will be either a string or int
 */
public class LinkedBinarySearchTree<E extends Comparable<E>>implements BinarySearchTree<E> {
    private E data; //Generic for inputting into tree. This is basically the candidates information

    public E getData() { //Getter for data
        return data;
    }

    public void setData(E data) { //setter for data
        this.data = data;
    }

    public LinkedBinarySearchTree<E> getLeftSubTree() { //getter for left subtree
        return leftSubTree;
    }

    public void setLeftSubTree(LinkedBinarySearchTree<E> leftSubTree) { //setter for right subtree
        this.leftSubTree = leftSubTree;
    }

    public LinkedBinarySearchTree<E> getRightSubTree() { //getter for right subtree
        return rightSubTree;
    }

    public void setRightSubTree(LinkedBinarySearchTree<E> rightSubTree) { //setter for right subtree
        this.rightSubTree = rightSubTree;
    }

    public int getCount() { //getter for count
        return count;
    }

    public void setCount(int count) { //setter for count
        this.count = count;
    }

    private LinkedBinarySearchTree<E> leftSubTree; //Represents the left subtree
    private LinkedBinarySearchTree<E> rightSubTree; //Represent the right subtree
    private int count; //This counts the size of the tree
    public LinkedBinarySearchTree() {this.data = null; leftSubTree = null; rightSubTree = null;} //Initializing all 3 things as null so that we can start soring the elements correctly


    /**
     * This is a constructor that initializes the four parts of the binary search tree that will be necessary in order for it to print correctly
     * @param element The current candidates' data that we are on. Also applicable for the current letter or number we are on
     */
    public LinkedBinarySearchTree(E element){
        this.data = element; //Initializing this.data to element
        leftSubTree = new LinkedBinarySearchTree<E>(); //Creating the left subtree as its own tree
        rightSubTree = new LinkedBinarySearchTree<E>(); //Creating the right subtree as its own tree
        count ++; //Incrementing count
    }

    /**
     * Returns the element of the root of the tree
     * @return A singular element of the binary tree
     */
    public E getRootElement() {
        return data; //returns data
    }

    /**
     * Checks if the tree isEmpty
     * @return A boolean statement stating if the tree is empty or not
     */
    public boolean isEmpty() {
        return count == 0; //If it is empty return that the count is 0
    }

    /**
     * This method tell the user the size fo the tree
     * @return the integer for the size of the tree
     */
    public int size() {
        return count; //Returns the count (size) of the tree
    }

    /**
     * This method helps insert elements into a binary tree
     * @param element is the information that is stored with each part of the tree
     */
    public void insert(E element) {
        if (data == null) { //If the data is null
            data = element; //The variable data is equal to element
        } else if (data.compareTo(element) == 0){ //If the compareTo equals 0
            data = element; //Data equals element
        } else if (data.compareTo(element) < 0) { //If the compareTo is less than 0
            if (rightSubTree == null) { //If the right subtree is null
                rightSubTree = new LinkedBinarySearchTree<>(); //Create a new right subtree
                recursiveInsert(element, rightSubTree); //Using the helper method insert this element into the right subtree
            } else{
                recursiveInsert(element, rightSubTree); //If we already have elements in the tree, just insert it using the helper method
            }
        } else {
            if (leftSubTree == null) { //If the left subtree is null
                leftSubTree = new LinkedBinarySearchTree<>(); //Create a new left subtree
                recursiveInsert(element, leftSubTree); //Using the helper method insert this element into the left subtree
            } else {
                recursiveInsert(element, leftSubTree); //If we already have elements in the tree, just insert it using the helper method
            }
        }
        count++; //Increment the count every time
    }

    /**
     * This is a helper method that helps us with the insert function above
     * @param element is the information that is stored with each part of the tree
     * @param root Root of the left or right subtree
     */
    private void recursiveInsert(E element, LinkedBinarySearchTree<E> root){
        if (root.data == null) { //If root.data is null
            root.data = element; //root.data is now element
        }else if (root.data.compareTo(element) == 0){ //If the compareTo equals 0
            root.data = element; //root.data equals element
            count--; //Decrease count by 1
        } else {
            if (root.data.compareTo(element) < 0) { //If the compareTo is less than 0
                if (root.rightSubTree == null) { //If the right subtree is null
                    root.rightSubTree = new LinkedBinarySearchTree<>(); //Create a new right subtree
                }
                recursiveInsert(element, root.rightSubTree); //Using the helper method insert this element into the right subtree
            } else {
                if (root.leftSubTree == null) { //If the left subtree is null
                    root.leftSubTree = new LinkedBinarySearchTree<>(); //Create a new left subtree
                }
                recursiveInsert(element, root.leftSubTree); //Using the helper method insert this element into the left subtree
            }
        }

    }

    @Override
    /**
     * This is a method that will print the elements in a binary tree in the inOrder way of the tree. Goes left, root and right
     */
    public String toStringInOrder() {
        String str = ""; //First set the string to nothing
        if (leftSubTree != null) { //If the tree isn't null
            str += leftSubTree.toStringInOrder() + " "; //Put all of these elements into the one large sentence of elements
        }
        str += data.toString(); //Add the root to the large sentence of elements
        if (rightSubTree != null) {  //If the tree isn't null
            str += " " + rightSubTree.toStringInOrder(); //Put all of these elements into the one large sentence of elements
        } else {
            str += ""; //Otherwise, the string is empty
        }
        return str; //Return the string
    }

    @Override
    /**
     * This is a method that will print the elements in a binary tree in the preOrder way of the tree. Goes root, left and right
     */
    public String toStringPreOrder() {
        String str = ""; //First set the string to nothing
        str += "" + data.toString(); //Add the root to the large sentence of elements
        if (leftSubTree != null) { //If the tree isn't null
            str += " " + leftSubTree.toStringPreOrder(); //Put all of these elements into the one large sentence of elements
        }
        if (rightSubTree != null) { //If the tree isn't null
            str += " " + rightSubTree.toStringPreOrder(); //Put all of these elements into the one large sentence of elements
        }
        return str; //Return the string
    }

    @Override
    /**
     * This is a method that will print the elements in a binary tree in the postOrder way of the tree. Goes left, right and root
     */
    public String toStringPostOrder() {
        String str = ""; //First set the string to nothing
        if (leftSubTree != null) { //If the tree isn't null
            str += leftSubTree.toStringPostOrder() + " "; //Put all of these elements into the one large sentence of elements
        }
        if (rightSubTree != null) { //If the tree isn't null
            str += rightSubTree.toStringPostOrder() + " ";//Put all of these elements into the one large sentence of elements
        }
        str += data.toString(); //Add the root to the large sentence of elements
        return str; //Return the string
    }

    /**
     * A toString function that will print out the three toStrings to the user
     * @return The statement of the three orderings of the tress
     */
    public String toString() {
        return "Tree: \nPre:  " + toStringPreOrder() + "\nIn:   " + toStringInOrder() + "\nPost: " + toStringPostOrder();//Return this sentence to the user
    }

}
