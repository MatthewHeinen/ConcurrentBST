package src;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        /**
         * Single threaded test suite. Do I need more than 2 tests? These were just taken from my 106 lab
         * Also, should I have two insert functions. One for the single thread and one for the multithread?
         */
//        System.out.println("Running Our Sequential Tests");
//        System.out.println("Sequential Test 1");
//        BST_class bstSequntialOne = new BST_class(); //Example 1 test
//        bstSequntialOne.timing();
//        bstSequntialOne.insert(8);
//        bstSequntialOne.insert(11);
//        bstSequntialOne.insert(5);
//        bstSequntialOne.insert(17);
//        bstSequntialOne.insert(10);
//        bstSequntialOne.insert(1);
//        bstSequntialOne.insert(9);
//        bstSequntialOne.insert(3);
//        System.out.println("In order printing of a tree with no removes in it: ");
//        System.out.println("Size: " + bstSequntialOne.size());
//        bstSequntialOne.inorder();
//        System.out.println("\n" + "This is a "+ bstSequntialOne.invariantChecker() + " BST");
//        bstSequntialOne.deleteKey(3);
//        bstSequntialOne.deleteKey(11);
//        bstSequntialOne.deleteKey(1);
//        System.out.println("\n");
//        System.out.println("Size: " + bstSequntialOne.size());
//        System.out.println("In order printing of a tree with a remove/removes in it: ");
//        bstSequntialOne.inorder();
//        System.out.println("\n" + "This is a "+ bstSequntialOne.invariantChecker() + " BST");
//        System.out.println("\n");
//        System.out.println("A false BST test when the nodes are printed in order: ");
//        bstSequntialOne.falseBST();
//        bstSequntialOne.inorder();
//        System.out.println("\n" + "This is a "+ bstSequntialOne.invariantChecker() + " BST");
//        System.out.println("*****************************************************************************");

//        System.out.println("Sequential Test 2");
//        BST_class bstSequentialTwo = new BST_class();
//        bstSequentialTwo.timing();
//        System.out.println("The element 6 when searched for in the empty tree is false: " + bstSequentialTwo.search(6));
//        bstSequentialTwo.insert(-5);
//        bstSequentialTwo.insert(-1);
//        bstSequentialTwo.insert(2);
//        bstSequentialTwo.insert(5);
//        bstSequentialTwo.insert(10);
//        System.out.println("In order printing of a tree with no removes in it: ");
//        System.out.println("Size: " + bstSequentialTwo.size());
//        bstSequentialTwo.inorder();
//        System.out.println("\n" + "This is a "+ bstSequentialTwo.invariantChecker() + " BST");
//        System.out.println("The element -1 when searched for in the empty tree is true: " + bstSequentialTwo.search(-1));
//        bstSequentialTwo.deleteKey(-5);
//        bstSequentialTwo.deleteKey(-1);
//        System.out.println("\n");
//        System.out.println("Size: " + bstSequentialTwo.size());
//        System.out.println("In order printing of a tree with a remove/removes in it: ");
//        bstSequentialTwo.inorder();
//        System.out.println("\n" + "This is a "+ bstSequentialTwo.invariantChecker() + " BST");
//        System.out.println("The element -1 when searched for when it was just deleted in the tree is false: " + bstSequentialTwo.search(-1));
//        System.out.println("End of the sequential tests");
//        System.out.println("**********************************************************************");
//        System.out.println("Start of the concurrent tests");
//        System.out.println("Concurrent Test 1");
//
//        BST_class bstMulti = new BST_class(); //Example 1 test
//        bstMulti.timing();
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
////                System.out.println("Bye from Thread 8");
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
////                    System.out.println("\n" + "This is a "+ bstMulti.invariantChecker() + " BST");
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
////                System.out.println("Bye from Thread 9");
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
////                System.out.println("Bye from Thread 10");
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
////                System.out.println("Bye from Thread 11");
//            }
//        });
//        insert11.start();
//
//        insert1.join();
//        insert2.join();
//        insert3.join();
//        insert4.join();
//        insert5.join();
//        insert6.join();
//        insert7.join();
//        insert8.join();
//        insert9.join();
//        insert10.join();
//        insert11.join();
//
//        System.out.println("In order printing of a tree with no removes in it: ");
//        System.out.println("Size: " + bstMulti.size());
//        bstMulti.inorder();
//        System.out.println("\n");
//        System.out.println("This is a "+ bstMulti.invariantChecker() + " BST");
//
//        Thread remove1 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    bstMulti.deleteKey(4);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });
//        remove1.start();
//
//        Thread remove2 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    bstMulti.deleteKey(15);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });
//        remove2.start();
//
//        Thread remove3 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    bstMulti.deleteKey(100);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });
//        remove3.start();
//
//        Thread remove4 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    bstMulti.deleteKey(3);
//                    bstMulti.deleteKey(20);
//                    bstMulti.deleteKey(6);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });
//        remove4.start();
//
//        remove1.join();
//        remove2.join();
//        remove3.join();
//        remove4.join();
//
//
//        System.out.println("\n");
//        System.out.println("In order printing of a tree with removes in it: ");
//        System.out.println("Size: " + bstMulti.size());
//        bstMulti.inorder();
//        System.out.println("\n" + "This is a "+ bstMulti.invariantChecker() + " BST");
        System.out.println("********************************************************************");
        System.out.println("Concurrent Test 2");

        BST_class bstMultiTwo = new BST_class(); //Example 1 test
        bstMultiTwo.timing();
        Thread ins1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    bstMultiTwo.insert(5);
                    bstMultiTwo.insert(9);
                    bstMultiTwo.insert(15);
                    bstMultiTwo.insert(20);
                    bstMultiTwo.insert(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        ins1.start();

        Thread ins2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    bstMultiTwo.insert(6);
                    bstMultiTwo.insert(10);
                    bstMultiTwo.insert(16);
                    bstMultiTwo.insert(21);
                    bstMultiTwo.insert(0);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        ins2.start();

        Thread ins3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    bstMultiTwo.insert(35);
                    bstMultiTwo.insert(36);
                    bstMultiTwo.insert(37);
                    bstMultiTwo.insert(38);
                    bstMultiTwo.insert(39);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        ins3.start();

        ins1.join();
        ins2.join();
        ins3.join();

        System.out.println("In order printing of a tree with no removes in it: ");
        System.out.println("Size: " + bstMultiTwo.size());
        bstMultiTwo.inorder();
        System.out.println("\n");
        System.out.println("The element 10 when searched for when it was just inserted in the tree is true: " + bstMultiTwo.search(10));
        System.out.println("This is a "+ bstMultiTwo.invariantChecker() + " BST");

        Thread del1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    bstMultiTwo.deleteKey(20);
                    bstMultiTwo.deleteKey(21);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        del1.start();

        Thread del2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //bstMultiTwo.invariantChecker();
                    bstMultiTwo.deleteKey(10);
                    bstMultiTwo.deleteKey(15);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        del2.start();

        Thread del3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    bstMultiTwo.deleteKey(36);
                    bstMultiTwo.deleteKey(37);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        del3.start();

        del1.join();
        del2.join();
        del3.join();

        System.out.println("\n");
        System.out.println("In order printing of a tree with removes in it: ");
        System.out.println("Size: " + bstMultiTwo.size());
        bstMultiTwo.inorder();
        System.out.println("\n" + "This is a "+ bstMultiTwo.invariantChecker() + " BST");
    }
}
