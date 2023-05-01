package src;

/**
 * Interface with the methods of a binary search tree
 * @param <E> is a generic. Will be either a string or int
 */
public interface BinarySearchTree<E extends Comparable<E> > {
    void insert(E element); //Inserts an element in the tree, keeping it a BST
    public E deleteRec(LinkedBinarySearchTree<E> root, E element);
    public E deleteKey(E element);
    E getRootElement(); //Returns the element at the root of the tree
    int size(); //Returns the number of elements in the tree
    boolean isEmpty(); //Checks if tree is empty
    String toStringInOrder(); // Returns the elements as an in-order string
    String toStringPreOrder(); // Returns the elements as a pre-order string
    String toStringPostOrder(); // Returns the elements as a post-order string
}

