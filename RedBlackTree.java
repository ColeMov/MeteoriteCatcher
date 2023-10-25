// --== CS400 Fall 2023 File Header Information ==--
// Name: Arnav Srivastav
// Email: asrivastav32wisc.edu
// Group: C35
// TA: Alexander Peseckis
// Lecturer: Florian Heimerl

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import static  org.junit.jupiter.api.Assertions.assertEquals;



 public class RedBlackTree<T extends Comparable<T>> extends BinarySearchTree<T> {


     protected static class RBTNode<T> extends Node<T> {
         public int blackHeight = 0;

         public RBTNode(T data) {
             super(data);
         }

         public RBTNode<T> getUp() {
             return (RBTNode<T>) this.up;
         }

         public RBTNode<T> getDownLeft() {
             return (RBTNode<T>) this.down[0];
         }

         public RBTNode<T> getDownRight() {
             return (RBTNode<T>) this.down[1];
         }
     }

     /**
      * This method enforces Red-Black Tree properties after inserting a red node.
      * It ensures that the tree remains balanced and satisfies RBTree properties.
      *
      * @param redNode The red node that was inserted.
      */
     protected void enforceRBTreePropertiesAfterInsert(RBTNode<T> redNode) {
         RBTNode<T> parent = redNode.getUp();

         // Check if the parent is null or has a black height of 1 (no action needed).
         if (parent == null || parent.blackHeight == 1) {
             return;
         } else {
             RBTNode grandParent = parent.getUp();
             RBTNode aunt = null;

             // Determine the aunt node based on the parent's position.
             if (parent.isRightChild()) {
                 aunt = grandParent.getDownLeft();
             } else if (grandParent != null) {
                 aunt = grandParent.getDownRight();
             }

             // Check conditions for adjusting black heights.
             if ((aunt != null && aunt.blackHeight == 0) && parent.blackHeight == 0) {
                 // Case: Parent, aunt, and grandparent are all red.
                 parent.blackHeight = 1;
                 aunt.blackHeight = 1;
                 grandParent.blackHeight = 0;

                 if (grandParent != null) {
                     enforceRBTreePropertiesAfterInsert(grandParent);
                 }
                 return;
             } else if (parent.blackHeight == 0 && (aunt == null || aunt.blackHeight == 1)) {
                 // Case: Parent is red, and aunt is black or null.
                 if (parent.isRightChild() && !redNode.isRightChild() || (!parent.isRightChild() && redNode.isRightChild())) {
                     // Perform rotations based on the relationship of nodes.
                     rotate(redNode, parent);
                     redNode = parent;
                     parent = redNode.getUp();
                 }

                 rotate(parent, grandParent);

                 // Swap black heights of parent and grandparent.
                 int x = grandParent.blackHeight;
                 grandParent.blackHeight = parent.blackHeight;
                 parent.blackHeight = x;

                 enforceRBTreePropertiesAfterInsert(grandParent);
             }
         }
     }

     @Override
     public boolean insert(T key) {
         // Create a new Red-Black Tree (RBT) node with the given key.
         RBTNode<T> Node = new RBTNode<>(key);

         // Insert the new node into the Red-Black Tree.
         insertHelper(Node);

         // Ensure that Red-Black Tree properties are maintained after insertion.
         enforceRBTreePropertiesAfterInsert(Node);

         // Check if the root is not null, and set the black height to 1 if it's not.
         if (root != null) {
             ((RBTNode<T>) root).blackHeight = 1;
             return true;
         }

         // Return false if the root is null, indicating an unsuccessful insertion.
         return false;

     }

     /**
      * Clears the tree by removing all elements and resetting the structure.
      */


     private RedBlackTree<Integer> testRBT;


     /**
      * JUnit test to test insertion with a red aunt and see if the violations are fixed properly
      */
     @Test
     public void testRedAunt() {
         testRBT = new RedBlackTree<>();
         testRBT.insert(5); // Insert a black root
         testRBT.insert(2); // Insert a red left child of the root
         testRBT.insert(8); // Insert a red right child of the root
         testRBT.insert(16); // Insert a node to be added (should be red)

         // Assertions for black heights and tree structure
         Assertions.assertEquals(((RBTNode<Integer>) testRBT.root.down[1].down[1]).blackHeight, 0); // Verify that node 16 (new node) is red
         Assertions.assertEquals(testRBT.toLevelOrderString(), "[ 5, 2, 8, 16 ]"); // Verify the representation of the Red-Black Tree
         Assertions.assertEquals(((RBTNode<Integer>) testRBT.root).blackHeight, 1); // Verify that the root is black
         Assertions.assertEquals(((RBTNode<Integer>) testRBT.root.down[1]).blackHeight, 1); // Verify that node 8 is recolored to black
         Assertions.assertEquals(((RBTNode<Integer>) testRBT.root.down[0]).blackHeight, 1); // Verify that node 2 is recolored to black
         Assertions.assertEquals(((RBTNode<Integer>) testRBT.root.down[1].down[1]).blackHeight, 0); // Verify that node 16 remains red
     }

     /**
      * JUnit test to test insertion with a black aunt and ensure the violations are fixed properly
      */
     @Test
     public void testBlackAunt() {
         testRBT = new RedBlackTree<Integer>();
         testRBT.insert(5); // Insert a black root
         testRBT.insert(2); // Insert a black left child of the root
         testRBT.insert(8); // Insert a black right child of the root
         testRBT.insert(16); // Insert a red right child of the black right child of the root
         testRBT.insert(21); // Insert the node to be added (should be red)

         // Check whether node 21 (new node) is red
         Assertions.assertEquals(((RBTNode<Integer>) testRBT.root.down[1].down[1]).blackHeight, 0);

         // Verify that the Red-Black Tree is balanced after the addition
         Assertions.assertEquals(testRBT.toLevelOrderString(), "[ 5, 2, 16, 8, 21 ]");

         // There should be a left rotation at node 8 and a color swap for the Red-Black Tree to be balanced
         Assertions.assertEquals(((RBTNode<Integer>) testRBT.root).blackHeight, 1); // Verify that the root is black
         Assertions.assertEquals(((RBTNode<Integer>) testRBT.root.down[1]).blackHeight, 1); // Verify that node 16 is black
         Assertions.assertEquals(((RBTNode<Integer>) testRBT.root.down[0]).blackHeight, 1); // Verify that node 2 is black
         Assertions.assertEquals(((RBTNode<Integer>) testRBT.root.down[1].down[0]).blackHeight, 0); // Verify that node 8 is red
         Assertions.assertEquals(((RBTNode<Integer>) testRBT.root.down[1].down[1]).blackHeight, 0); // Verify that node 21 is red
     }
     /**
      * JUnit test to verify insertion with a black aunt and ensure proper violation resolution.
      */
     @Test
     public void testInsertionWithBlackAunt() {

         // Create a Red-Black Tree instance.
         RedBlackTree<Integer> rbt = new RedBlackTree<>();

         // Insert some values into the tree.
         rbt.insert(10);
         rbt.insert(3);
         rbt.insert(2);
         rbt.insert(5);

         // Get the root of the tree.
         RBTNode<Integer> treeRoot = (RBTNode<Integer>) rbt.root;

         // Check if Red-Black Tree properties are maintained.
         if (treeRoot.blackHeight != 1 ||
                 treeRoot.getDownLeft().blackHeight != 1 ||
                 treeRoot.getDownRight().blackHeight != 1 ||
                 treeRoot.getDownRight().getDownLeft().blackHeight != 0) {
             Assertions.fail("Red-Black Tree properties are not properly maintained.");
         }
     }
 }