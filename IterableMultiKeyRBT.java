// --== CS400 Fall 2023 File Header Information ==--
// Name: Arnav Srivastav
// Email: asrivastav3@wisc.edu
// Group: C35
// TA: Alexander Peseckis
// Lecturer: Florian Heimerl

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.Stack;
import java.util.*;

public class IterableMultiKeyRBT<T extends Comparable<T>> extends RedBlackTree<KeyListInterface<T>> implements IterableMultiKeySortedCollectionInterface<T>{

    private Comparable<T> iterationStartPoint;
    private int numKeys = 0;

    /**
     * Inserts a single key into the tree.
     *
     * @param key the key to insert
     * @return true if a new node was inserted, false if the key was added to an existing node
     */
    @Override
    public boolean insertSingleKey(T key) {
        KeyListInterface<T> keyList = new KeyList<>(key);

        Node<KeyListInterface<T>> existingNode = findNode(keyList);

        if (existingNode != null) {
            existingNode.data.addKey(key);
            this.numKeys++;
            return false;
        } else {
            super.insert(keyList);
            this.numKeys++;
            return true;
        }
    }


    /**
     * Returns the number of keys in the tree.
     *
     * @return the number of values in the tree
     */
    @Override
    public int numKeys() {

        return this.numKeys;    }

    protected Stack<Node<KeyListInterface<T>>> getStartStack() {
        Stack<Node<KeyListInterface<T>>> stack = new Stack<>();
        Node<KeyListInterface<T>> currentNode = root;

        while (currentNode != null) {
            if (iterationStartPoint == null) {
                stack.push(currentNode);
                currentNode = currentNode.down[0];
            } else {
                int comparisonResult = iterationStartPoint.compareTo(currentNode.data.iterator().next());

                if (comparisonResult < 0) {
                    stack.push(currentNode);
                    currentNode = currentNode.down[0];
                } else if (comparisonResult > 0) {
                    currentNode = currentNode.down[1];
                } else {
                    stack.push(currentNode);
                    break;
                }
            }
        }
        return stack;
    }


    /**
     * Returns an iterator that does an in-order iteration over the tree.
     *
     * @return an iterator for in-order iteration
     */
    public Iterator<T> iterator() {
        Stack<Node<KeyListInterface<T>>> stack = getStartStack();
        return new Iterator<T>() {
            Iterator<T> currentKeyListIterator = (stack.isEmpty()) ? null : stack.peek().data.iterator();

            @Override
            public boolean hasNext() {
                // If the currentKeyListIterator is not null and has more elements, return true.
                if (currentKeyListIterator != null && currentKeyListIterator.hasNext()) {
                    return true;
                }

                // Otherwise, search for the next non-empty key list and update currentKeyListIterator.
                while (!stack.isEmpty()) {
                    Node<KeyListInterface<T>> node = stack.pop();
                    if (node.down[1] != null) {
                        stack.push(node.down[1]);
                        node = node.down[1];
                        while (node.down[0] != null) {
                            stack.push(node.down[0]);
                            node = node.down[0];
                        }
                    }

                    if (!stack.isEmpty()) {
                        currentKeyListIterator = stack.peek().data.iterator();
                        if (currentKeyListIterator.hasNext()) {
                            return true;
                        }
                    }
                }

                // No more elements found, return false.
                return false;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                return currentKeyListIterator.next();
            }
        };
    }


    /**
     * Sets the starting point for iterations. Future iterations will start at the
     * starting point or the key closest to it in the tree. This setting is
     * remembered until it is reset. Passing in null disables the starting point.
     *
     * @param startPoint the start point to set for iterations
     */
    @Override
    public void setIterationStartPoint(Comparable<T> startPoint) {

        this.iterationStartPoint = startPoint;
    }

    public void clear() {
        super.clear();
        this.numKeys = 0; // Reset the number of keys to zero
    }



    /**
     * Test basic iteration for the IterableMultiKeyRBT.
     */
    @Test
    public void testIterableMultiKeyRBTBasicIteration(){
        IterableMultiKeyRBT<String> tree = new IterableMultiKeyRBT<>();
        tree.insertSingleKey("M");
        tree.insertSingleKey("U");
        Iterator<String> iterator = tree.iterator();
        Assertions.assertEquals("M",iterator.next());
        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals("U",iterator.next());
        Assertions.assertFalse(iterator.hasNext());

    }

    @Test
    public void testIterableMultiKeyRBTBasicIteration2() {
        IterableMultiKeyRBT<Integer> tree = new IterableMultiKeyRBT<>();
        tree.insertSingleKey(5);
        tree.insertSingleKey(10);

        Iterator<Integer> iterator = tree.iterator();

        Assertions.assertEquals(5, iterator.next());
        Assertions.assertTrue(iterator.hasNext());

        Assertions.assertEquals(10, iterator.next());
        Assertions.assertFalse(iterator.hasNext());
    }

    /**
     * Checks if an iterator with a start point iterates over all keys
     * equal to and larger than the start point.
     */
    @Test
    public void testIteratorStartPoint() {
        IterableMultiKeyRBT<Integer> tree = new IterableMultiKeyRBT<>();
        tree.insertSingleKey(5);
        tree.insertSingleKey(25);
        tree.insertSingleKey(30);

        tree.setIterationStartPoint(20);
        Assertions.assertEquals(25, tree.iterator().next());
        tree.setIterationStartPoint(26);
        Assertions.assertEquals(30, tree.iterator().next());
        tree.setIterationStartPoint(null);
        Assertions.assertEquals(5, tree.iterator().next());
    }
    @Test
    public void submissionCheckerDuplicateKeys() {
        IterableMultiKeyRBT<Integer> tree = new IterableMultiKeyRBT<>();
        tree.insertSingleKey(50);
        tree.insertSingleKey(50);
        tree.insertSingleKey(100);
        tree.insertSingleKey(100);
        tree.insertSingleKey(150);
        tree.insertSingleKey(150);

        Assertions.assertEquals(3, tree.size());
        Assertions.assertEquals(6, tree.numKeys());

        int count = 0;
        Iterator<Integer> iter = tree.iterator();
        for (Integer key : tree) {
            int expected = ((count++/2)+1)*50;
            Assertions.assertEquals(expected, iter.next());
        }
    }



}
