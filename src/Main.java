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

//        BST_class bstMulti = new BST_class(); //Example 1 test
//        Thread insert1 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    bstMulti.insert(8);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });
//        insert1.start();
//
//        Thread insert2 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    bstMulti.insert(2);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });
//        insert2.start();
//
//        Thread insert3 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    bstMulti.insert(6);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });
//        insert3.start();
//
//        Thread insert4 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    bstMulti.insert(4);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });
//        insert4.start();
//
//        Thread insert5 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    bstMulti.insert(3);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });
//        insert5.start();
//
//        Thread insert6 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    bstMulti.insert(1);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });
//        insert6.start();
//
//        Thread insert7 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    bstMulti.insert(10);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });
//        insert7.start();
//
//        Thread insert8 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    bstMulti.insert(15);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });
//        insert8.start();
//
//        Thread insert9 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    bstMulti.insert(12);
//                    bstMulti.insert(20);
//                    bstMulti.insert(25);
//                    System.out.println("\n" + "This is a "+ bstMulti.invariantChecker() + " BST");
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });
//        insert9.start();
//
//        Thread insert10 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    bstMulti.insert(100);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });
//        insert10.start();
//
//        Thread insert11 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    bstMulti.insert(60);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });
//        insert11.start();

//        System.out.println("In order printing of a tree with no removes in it: ");
//        System.out.println("Size: " + bstMulti.size());
//        bstMulti.inorder();
//        System.out.println("\n" + "This is a "+ bstMulti.invariantChecker() + " BST");
//
//        Thread remove1 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                bstMulti.deleteKey(4);
//            }
//        });
//        remove1.start();
//
//        Thread remove2 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                bstMulti.deleteKey(15);
//            }
//        });
//        remove2.start();
//
//        Thread remove3 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                bstMulti.deleteKey(100);
//            }
//        });
//        remove3.start();
//
//        Thread remove4 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                bstMulti.deleteKey(3);
//            }
//        });
//        remove4.start();
//
//        System.out.println("\n");
//        System.out.println("In order printing of a tree with removes in it: ");
//        System.out.println("Size: " + bstMulti.size());
//        bstMulti.inorder();
//        System.out.println("\n" + "This is a "+ bstMulti.invariantChecker() + " BST");
    }
}
