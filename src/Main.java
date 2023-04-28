package src;

public class Main {
    public static void main(String[] args) {

        /**
         * Single threaded test suite. Do I need more than 2 tests? These were just taken from my 106 lab
         * Also, should I have two insert functions. One for the single thread and one for the multithread?
         */
        System.out.println("This is a starter project, nothing important in it.");
        BinarySearchTree<Integer> intTree = new LinkedBinarySearchTree<Integer>(); //Example 1 test
        intTree.insert(8);
        intTree.insert(11);
        intTree.insert(5);
        intTree.insert(17);
        intTree.insert(1);
        intTree.insert(9);
        intTree.insert(3);
        System.out.println("Size: " + intTree.size());
        System.out.println(intTree);


        BinarySearchTree<Character> letterTree = new LinkedBinarySearchTree<Character>(); //Example 2 test
        letterTree.insert('A');
        letterTree.insert('C');
        letterTree.insert('G');
        letterTree.insert('B');
        letterTree.insert('D');
        letterTree.insert('G'); // inserting again, should replace
        letterTree.insert('F');
        letterTree.insert('E');
        letterTree.insert('H');
        letterTree.insert('I');
        System.out.println("Size: " + letterTree.size());
        System.out.println(letterTree);
    }
}