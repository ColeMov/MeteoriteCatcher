// --== CS400 Fall 2023 File Header Information ==--
// Name: Cole Movsessian
// Email: movsessian@wisc.edu
// Group: C35
// TA: Alex Peseckis
// Lecturer: Florian
// Notes to Grader: <optional extra notes>

import java.util.LinkedList;
import java.util.Stack;


/**
 * Binary Search Tree implementation with a Node inner class for representing
 * the nodes of the tree. We will turn this Binary Search Tree into a self-balancing
 * tree as part of project 1 by modifying its insert functionality.
 * In week 0 of project 1, we will start this process by implementing tree rotations.
 */
public class BinarySearchTree<T extends Comparable<T>> implements SortedCollectionInterface<T> {

    /**
     * This class represents a node holding a single value within a binary tree.
     */
    protected static class Node<T> {
        public T data;

        // up stores a reference to the node's parent
        public Node<T> up;
        // The down array stores references to the node's children:
        // - down[0] is the left child reference of the node,
        // - down[1] is the right child reference of the node.
        // The @SupressWarning("unchecked") annotation is use to supress an unchecked
        // cast warning. Java only allows us to instantiate arrays without generic
        // type parameters, so we use this cast here to avoid future casts of the
        // node type's data field.
        @SuppressWarnings("unchecked")
        public Node<T>[] down = (Node<T>[])new Node[2];
        public Node(T data) { this.data = data; }
        
        /**
         * @return true when this node has a parent and is the right child of
         * that parent, otherwise return false
         */
        public boolean isRightChild() {
            return this.up != null && this.up.down[1] == this;
        }

    }

    protected Node<T> root; // reference to root node of tree, null when empty
    protected int size = 0; // the number of values in the tree

    /**
     * Inserts a new data value into the tree.
     * This tree will not hold null references, nor duplicate data values.
     * @param data to be added into this binary search tree
     * @return true if the value was inserted, false if is was in the tree already
     * @throws NullPointerException when the provided data argument is null
     */
    public boolean insert(T data) throws NullPointerException {
        if (data == null)
			throw new NullPointerException("Cannot insert data value null into the tree.");
		return this.insertHelper(new Node<>(data));
    }

    /**
     * Performs a naive insertion into a binary search tree: adding the new node
     * in a leaf position within the tree. After this insertion, no attempt is made
     * to restructure or balance the tree.
     * @param node the new node to be inserted
     * @return true if the value was inserted, false if is was in the tree already
     * @throws NullPointerException when the provided node is null
     */
    protected boolean insertHelper(Node<T> newNode) throws NullPointerException {
        if(newNode == null) throw new NullPointerException("new node cannot be null");

        if (this.root == null) {
            // add first node to an empty tree
            root = newNode;
            size++;
            return true;
        } else {
            // insert into subtree
            Node<T> current = this.root;
            while (true) {
                int compare = newNode.data.compareTo(current.data);
                if (compare == 0) {
                	return false;
				} else if (compare < 0) {
                    // insert in left subtree
                    if (current.down[0] == null) {
                        // empty space to insert into
                        current.down[0] = newNode;
                        newNode.up = current;
                        this.size++;
                        return true;
                    } else {
                        // no empty space, keep moving down the tree
                        current = current.down[0];
                    }
                } else {
                    // insert in right subtree
                    if (current.down[1] == null) {
                        // empty space to insert into
                        current.down[1] = newNode;
                        newNode.up = current;
                        this.size++;
                        return true;
                    } else {
                        // no empty space, keep moving down the tree
                        current = current.down[1]; 
                    }
                }
            }
        }
    }

    /**
     * Performs the rotation operation on the provided nodes within this tree.
     * When the provided child is a left child of the provided parent, this
     * method will perform a right rotation. When the provided child is a
     * right child of the provided parent, this method will perform a left rotation.
     * When the provided nodes are not related in one of these ways, this method
     * will throw an IllegalArgumentException.
     * @param child is the node being rotated from child to parent position
     *      (between these two node arguments)
     * @param parent is the node being rotated from parent to child position
     *      (between these two node arguments)
     * @throws IllegalArgumentException when the provided child and parent
     *      node references are not initially (pre-rotation) related that way
     */
    protected void rotate(Node<T> child, Node<T> parent) throws IllegalArgumentException {
        // TODO: Implement this method.
        if(child.up != parent) {
            throw new IllegalArgumentException();
        }else if(child.isRightChild()) {
            //left rotation
        	parent.down[1] = child.down[0];
        	child.down[0] = parent;
        	if(parent.up == null) {
        		child.up = null;
        		this.root = child;
        	}else {
        		Node<T> grandpa = parent.up;
        		child.up = grandpa;
        		if(grandpa.down[0].equals(parent)) {
        			grandpa.down[0] = child;
        		}else {
        			grandpa.down[1] = child;
        		}
        	}
        	parent.up = child;
        }else {
            //right rotation
        	parent.down[0] = child.down[1];
        	child.down[1] = parent;
        	if(parent.up == null) {
        		child.up = null;
        		this.root = child;
        	}else {
        		Node<T> grandma = parent.up;
        		child.up = grandma;
        		if(grandma.down[0].equals(parent)) {
        			grandma.down[0] = child;
        		}else {
        			grandma.down[1] = child;
        		}
        	}
        	parent.up = child;
        }
    }

	/**
     * Get the size of the tree (its number of nodes).
     * @return the number of nodes in the tree
     */
    public int size() {
        return size;
    }

    /**
     * Method to check if the tree is empty (does not contain any node).
     * @return true of this.size() returns 0, false if this.size() != 0
     */
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * Checks whether the tree contains the value *data*.
     * @param data a comparable for the data value to check for
     * @return true if *data* is in the tree, false if it is not in the tree
     */
    public boolean contains(Comparable<T> data) {
        // null references will not be stored within this tree
        if (data == null) {
            throw new NullPointerException("This tree cannot store null references.");
        } else {
            Node<T> nodeWithData = this.findNode(data);
            // return false if the node is null, true otherwise
            return (nodeWithData != null);
        }
    }

    /**
     * Removes all keys from the tree.
     */
    public void clear() {
        this.root = null;
        this.size = 0;
    }

    /**
     * Helper method that will return the node in the tree that contains a specific
     * key. Returns null if there is no node that contains the key.
     * @param data the data value for which we want to find the node that contains it
     * @return the node that contains the data value or null if there is no such node
     */
    protected Node<T> findNode(Comparable<T> data) {
        Node<T> current = this.root;
        while (current != null) {
            int compare = data.compareTo(current.data);
            if (compare == 0) {
                // we found our value
                return current;
            } else if (compare < 0) {
                if (current.down[0] == null) {
                    // we have hit a null node and did not find our node
                    return null;
                }
                // keep looking in the left subtree
                current = current.down[0];
            } else {
                if (current.down[1] == null) {
                    // we have hit a null node and did not find our node
                    return null;
                }
                // keep looking in the right subtree
                current = current.down[1];
            }
        }
        return null;
    }

    /**
     * This method performs an inorder traversal of the tree. The string 
     * representations of each data value within this tree are assembled into a
     * comma separated string within brackets (similar to many implementations 
     * of java.util.Collection, like java.util.ArrayList, LinkedList, etc).
     * @return string containing the ordered values of this tree (in-order traversal)
     */
    public String toInOrderString() {
        // generate a string of all values of the tree in (ordered) in-order
        // traversal sequence
        StringBuffer sb = new StringBuffer();
        sb.append("[ ");
        if (this.root != null) {
            Stack<Node<T>> nodeStack = new Stack<>();
            Node<T> current = this.root;
            while (!nodeStack.isEmpty() || current != null) {
                if (current == null) {
                    Node<T> popped = nodeStack.pop();
                    sb.append(popped.data.toString());
                    if(!nodeStack.isEmpty() || popped.down[1] != null) sb.append(", ");
                    current = popped.down[1];
                } else {
                    nodeStack.add(current);
                    current = current.down[0];
                }
            }
        }
        sb.append(" ]");
        return sb.toString();
    }

    /**
     * This method performs a level order traversal of the tree. The string
     * representations of each data value
     * within this tree are assembled into a comma separated string within
     * brackets (similar to many implementations of java.util.Collection).
     * This method will be helpful as a helper for the debugging and testing
     * of your rotation implementation.
     * @return string containing the values of this tree in level order
     */
    public String toLevelOrderString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[ ");
        if (this.root != null) {
            LinkedList<Node<T>> q = new LinkedList<>();
            q.add(this.root);
            while(!q.isEmpty()) {
                Node<T> next = q.removeFirst();
                if(next.down[0] != null) q.add(next.down[0]);
                if(next.down[1] != null) q.add(next.down[1]);
                sb.append(next.data.toString());
                if(!q.isEmpty()) sb.append(", ");
            }
        }
        sb.append(" ]");
        return sb.toString();
    }

    public String toString() {
        return "level order: " + this.toLevelOrderString() +
                "\nin order: " + this.toInOrderString();
    }

    // Implement at least 3 tests using the methods below. You can
    // use your notes from lecture for ideas of rotation examples to test with.
    // Make sure to include rotations at the root of a tree in your test cases.
    // Give each of the methods a meaningful header comment that describes what is being
    // tested and make sure your tests have inline comments that help with reading your test code.
    // If you'd like to add additional tests, then name those methods similar to the ones given below.
    // Eg: public static boolean test4() {}
    // Do not change the method name or return type of the existing tests.
    // You can run your tests through the static main method of this class.
    
    /*
     * Tests right rotation of binary search tree at root
     */
    public static boolean test1() {
        // Creates test bst
    	BinarySearchTree bst = new BinarySearchTree();
        bst.insert(50);
        bst.insert(30);
        bst.insert(10);
        // Rotates bst and returns true if done correctly
        bst.rotate(bst.findNode(30), bst.findNode(50));
        if(bst.findNode(30).down[0].equals(bst.findNode(10)) && bst.findNode(30).down[1].equals(bst.findNode(50)) && bst.findNode(30).equals(bst.root)) {
        	return true;
        }
        return false;
    }
    
    /*
     * Tests left rotation of binary search tree from subtree
     */
    public static boolean test2() {
    	// Creates new test bst
        BinarySearchTree bst2 = new BinarySearchTree();
        bst2.insert(50);
        bst2.insert(40);
        bst2.insert(60);
        bst2.insert(65);
        bst2.insert(70);
        // Rotates the subtree and returns true if done correctly
        bst2.rotate(bst2.findNode(65), bst2.findNode(60));
        if(bst2.findNode(65).down[0].equals(bst2.findNode(60)) && bst2.findNode(65).down[1].equals(bst2.findNode(70)) && bst2.findNode(65).up.equals(bst2.findNode(50))) {
        	return true;
        }
        return false;
    }
    
    /*
     *  Tests invalid input for rotate method
     */
    public static boolean test3() {
        // Creates valid bst
    	BinarySearchTree bst3 = new BinarySearchTree();
    	bst3.insert(30);
    	bst3.insert(20);
    	bst3.insert(400);
    	// Incorrectly calls rotation method and should return true if error is received
    	try {
    		bst3.rotate(bst3.findNode(400), bst3.findNode(20));
    	}catch(IllegalArgumentException e){
    		return true;
    	}
        return false;
    }
    
    /*
     *  Tests the left rotation that my program failed on the first submission
     */
    public static boolean test4() {
    	// Creates test BST
    	BinarySearchTree bst4 = new BinarySearchTree();
    	bst4.insert(4);
    	bst4.insert(2);
    	bst4.insert(1);
    	bst4.insert(3);
    	bst4.insert(6);
    	bst4.insert(5);
    	bst4.insert(7);
    	// Tests if properly rotated and returns true is correct
    	bst4.rotate(bst4.findNode(3), bst4.findNode(2));
    	if(bst4.toLevelOrderString().equals("[ 4, 3, 6, 2, 5, 7, 1 ]")) {
    		return true;
    	}
    	return false;
    }
    
    /**
     * Main method to run tests. If you'd like to add additional test methods, add a line for each
     * of them.
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Test 1 passed: " + test1());
        System.out.println("Test 2 passed: " + test2());
        System.out.println("Test 3 passed: " + test3());
        System.out.println("Test 4 passed: " + test4());
    }

}

