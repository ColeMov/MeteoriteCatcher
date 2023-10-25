// --== CS400 Fall 2023 File Header Information ==--
// Name: Cole Movsessian
// Email: movsessian@wisc.edu
// Group: C35
// TA: Alex Peseckis
// Lecturer: Florian
// Notes to Grader: <optional extra notes>
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class RedBlackTree<T extends Comparable<T>> extends BinarySearchTree<T>{

    /**
     * Stores the color of each node in addition to node's parent, children, and data
     * @param <T>
     */
    protected static class RBTNode<T> extends Node<T> {
        public int blackHeight = 0;
        public RBTNode(T data) { super(data); }
        public RBTNode<T> getUp() { return (RBTNode<T>)this.up; }
        public RBTNode<T> getDownLeft() { return (RBTNode<T>)this.down[0]; }
        public RBTNode<T> getDownRight() { return (RBTNode<T>)this.down[1]; }
    }

    /**
     * Resolves any red property violations after new node insertion
     * @param redNode new node in reference
     */
    protected void enforceRBTreePropertiesAfterInsert(RBTNode redNode){

        while(redNode.getUp() != null && redNode.getUp().blackHeight == 0){
            RBTNode parent = redNode.getUp();
            RBTNode uncle = null;
            if(parent.isRightChild()){
                uncle = parent.getUp().getDownLeft();

                if(uncle != null && uncle.blackHeight == 0){
                    parent.blackHeight = 1;
                    uncle.blackHeight = 1;
                    parent.getUp().blackHeight = 0;
                    redNode = parent.getUp();
                    continue;
                }
                if(!redNode.isRightChild()){
                    rotate(redNode,redNode.getUp());
                    redNode = redNode.getUp();
                }
                if(redNode.getUp() != null){
                    redNode.getUp().blackHeight = 1;
                    redNode.getUp().getUp().blackHeight = 0;
                    rotate(redNode.getUp(),redNode.getUp().getUp());
                }
            } else {
                uncle = parent.getUp().getDownRight();

                if(uncle != null && uncle.blackHeight == 0){
                    parent.blackHeight = 1;
                    uncle.blackHeight = 1;
                    parent.getUp().blackHeight = 0;
                    redNode = parent.getUp();
                    continue;
                }
                if(redNode.isRightChild()){
                    rotate(redNode,redNode.getUp());
                    redNode = redNode.getUp();
                }
                if(redNode.getUp() != null){
                    redNode.getUp().blackHeight = 1;
                    redNode.getUp().getUp().blackHeight = 0;
                    rotate(redNode.getUp(),redNode.getUp().getUp());
                }
            }
        }
        ((RBTNode)this.root).blackHeight = 1;
    }

    /**
     * Helper method to determine whether a node has children
     * @param node inserted node
     * @return true if leaf node, false if not
     */
    private boolean isLeafNode(RBTNode node){
        if(node.getDownRight() == null && node.getDownLeft() == null){
            return true;
        }
        return false;
    }

    @Override
    /**
     * Inserts a new data value into the tree. This tree will not hold null references, nor duplicate data values.
     * @param data to be added into this binary search tree
     * @return true if the value was inserted, false if is was in the tree already
     * @throws NullPointerException when the provided data argument is null
     */
    public boolean insert(T data) throws NullPointerException {
        if (data == null)
            throw new NullPointerException("Cannot insert data value null into the tree.");
        RBTNode<T> newNode = new RBTNode<>(data);
        this.insertHelper(newNode);
        this.enforceRBTreePropertiesAfterInsert(newNode);
        ((RBTNode<T>)this.root).blackHeight = 1;
        return true;
    }

    /**
     * JUnit test 1 with emphasis on recolor operation
     */
    @Test
    public void RBTreeTest1() {
        RedBlackTree RBT = new RedBlackTree();
        RBT.insert(10);
        RBT.insert(5);
        RBT.insert(15);
        //next node requires recolor
        RBT.insert(3);
        RBT.insert(7);
        RBT.insert(18);
        //next node requires recolor
        RBT.insert(2);
        //next node requires recolor and rotation
        RBT.insert(1);
        //next node requires recolor and rotation
        RBT.insert(20);
        Assertions.assertEquals(((RBTNode<T>)RBT.root).blackHeight,1);
        if(RBT.toLevelOrderString().equals("[ 10, 5, 18, 2, 7, 15, 20, 1, 3 ]")){
            Assertions.assertEquals(((RBTNode)RBT.root).blackHeight,1);
            Assertions.assertTrue(true);
        }else{
            System.out.println(RBT.toLevelOrderString());
            Assertions.fail("RBT properties are not properly enforced");
        }
    }

    /**
     * JUnit test 2 with emphasis on both rotation and recolor
     */
    @Test
    public void RBTreeTest2() {
        RedBlackTree RBT = new RedBlackTree();
        RBT.insert(15);
        RBT.insert(7);
        RBT.insert(17);
        //next node requires recolor
        RBT.insert(12);
        //next node requires recolor and rotations
        RBT.insert(13);
        //next node requires recolor
        RBT.insert(16);
        //next node requires recolor and rotation
        RBT.insert(20);
        //next node requires recolor, rotations, and root change
        RBT.insert(22);
        Assertions.assertEquals(((RBTNode<T>)RBT.root).blackHeight,1);
        if(RBT.toLevelOrderString().equals("[ 15, 12, 17, 7, 13, 16, 20, 22 ]")){
            Assertions.assertEquals(((RBTNode)RBT.root).blackHeight,1);
            Assertions.assertTrue(true);
        }else{
            System.out.println(RBT.toLevelOrderString());
            Assertions.fail("RBT properties are not properly enforced");
        }
    }

    /**
     * JUnit test 3 with emphasis on root change
     */
    @Test
    public void RBTreeTest3() {
        RedBlackTree RBT = new RedBlackTree();
        RBT.insert(69);
        RBT.insert(80);
        //next node requires recolor, rotation, and root change
        RBT.insert(88);
        //next node requires recolor
        RBT.insert(70);
        //next node requires recolor and rotation
        RBT.insert(71);
        //next node requires recolor
        RBT.insert(65);
        //next node requires recolor and rotation
        RBT.insert(62);
        //next node requires recolor, rotations, and root change
        RBT.insert(60);
        Assertions.assertEquals(((RBTNode<T>)RBT.root).blackHeight,1);
        if(RBT.toLevelOrderString().equals("[ 70, 65, 80, 62, 69, 71, 88, 60 ]")){
            Assertions.assertEquals(((RBTNode)RBT.root).blackHeight,1);
            Assertions.assertTrue(true);
        }else{
            System.out.println(RBT.toLevelOrderString());
            Assertions.fail("RBT properties are not properly enforced");
        }
    }
}
