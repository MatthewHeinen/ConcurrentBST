package src;

public class Main {
    public static void main(String[] args) {

        /**
         * Single threaded test suite. Do I need more than 2 tests? These were just taken from my 106 lab
         * Also, should I have two insert functions. One for the single thread and one for the multithread?
         */
        System.out.println("This is a starter project, nothing important in it.");
        BST_class bst = new BST_class(); //Example 1 test
        bst.insert(8);
        bst.insert(11);
        bst.insert(5);
        bst.insert(17);
        bst.insert(10);
        bst.insert(1);
        bst.insert(9);
        bst.insert(3);
        System.out.println("In order printing of a tree with no removes in it: ");
        System.out.println("Size: " + bst.size());
        bst.inorder();
        System.out.println("\n" + "This is a "+ bst.invariantChecker() + " BST");
        bst.deleteKey(3);
        bst.deleteKey(11);
        bst.deleteKey(1);
        System.out.println("\n");
        System.out.println("Size: " + bst.size());
        System.out.println("In order printing of a tree with a remove/removes in it: ");
        bst.inorder();
        System.out.println("\n" + "This is a "+ bst.invariantChecker() + " BST");
        System.out.println("\n");
        System.out.println("A false BST test: ");
        bst.falseBST();
        bst.inorder();
        System.out.println("\n" + "This is a "+ bst.invariantChecker() + " BST");
        System.out.println("*****************************************************************************");
    }
}
