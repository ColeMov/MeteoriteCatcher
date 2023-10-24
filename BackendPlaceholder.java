// --== CS400 Fall 2023 File Header Information ==--
// Name: Arnav Srivastav
// Email: asrivastav32@wisc.edu
// Group: C35
// TA: Alexander Peseckis
// Lecturer: Florian Heimerl

import java.util.ArrayList;
import java.util.Iterator;

public class BackendPlaceholder implements IterableMultiKeySortedCollectionInterface<Meteorite> {

    public ArrayList<Meteorite> meteorites = new ArrayList<>();

    @Override
    public boolean insertSingleKey(Meteorite key) {
        // Insert a single key into the collection
        int index = 0;

        for (int i = 0; i < this.meteorites.size(); i++) {
            if (key.getMass() > this.meteorites.get(i).getMass()) {
                index = i + 1;
            }
        }

        this.meteorites.add(index, key);
        return true;
    }

    /**
     * Get the number of keys in the collection.
     *
     * @return The number of keys in the collection.
     */
    @Override
    public int numKeys() {
        return 0;
    }

    /**
     * Return an iterator over the collection.
     *
     * @return An iterator over the collection.
     */
    @Override
    public Iterator<Meteorite> iterator() {
        return null;
    }

    /**
     * Set the starting point for iteration.
     *
     * @param startPoint The starting point for iteration.
     */
    @Override
    public void setIterationStartPoint(Comparable<Meteorite> startPoint) {
        // Set the starting point for iteration
    }

    /**
     * Insert a list of keys into the collection.
     *
     * @param data The list of keys to be inserted.
     * @return True if the insertion was successful, false otherwise.
     * @throws NullPointerException if the data is null.
     * @throws IllegalArgumentException if the data is invalid.
     */
    @Override
    public boolean insert(KeyListInterface<Meteorite> data) throws NullPointerException, IllegalArgumentException {
        // Insert a list of keys into the collection
        return false;
    }

    /**
     * Check if the collection contains a specific key.
     *
     * @param data The key to check for.
     * @return True if the collection contains the key, false otherwise.
     */
    @Override
    public boolean contains(Comparable<KeyListInterface<Meteorite>> data) {
        return false;
    }

    /**
     * Get the size of the collection.
     *
     * @return The size of the collection.
     */
    @Override
    public int size() {
        return this.meteorites.size();
    }

    /**
     * Check if the collection is empty.
     *
     * @return True if the collection is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return this.meteorites.isEmpty();
    }

    /**
     * Clear the collection.
     */
    @Override
    public void clear() {
        // Clear the collection
    }
}
