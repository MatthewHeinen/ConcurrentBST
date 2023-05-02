package src;

public class Main {
    public static void main(String[] args) throws InterruptedException {

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
        System.out.println("A false BST test when the nodes are printed in order: ");
        bst.falseBST();
        bst.inorder();
        System.out.println("\n" + "This is a "+ bst.invariantChecker() + " BST");
        System.out.println("*****************************************************************************");

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
        thread1.start();

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
        thread2.start();

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
        thread3.start();

        Thread thread4 = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
        thread4.start();

        Thread thread5 = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
        thread5.start();

        Thread thread6 = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
        thread6.start();

        Thread thread7 = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
        thread7.start();

        Thread thread8 = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
        thread8.start();

        Thread thread9 = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
        thread9.start();

        Thread thread10 = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
        thread10.start();
    }
}
