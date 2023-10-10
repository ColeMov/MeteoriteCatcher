import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for the Backend Developer's implementation of the Backend interface.
 * These tests verify the functionality of methods related to reading and managing meteorite data.
 */

public class BackendDeveloperTests  {

    private Backend backend;


    /**
     * Test the "getMeteoritesWithinMassRange" method to retrieve meteorites within a specified mass range.
     */
    @Test
    public void testMeteoriteAccessors() {
        // Create a sample Meteorite instance
        Meteorite meteorite = new Meteorite("Sample Meteorite", 30.0, -75.0, 128, 500.0);

        // Test accessor methods
        assertEquals("Sample Meteorite", meteorite.getName());
        assertEquals(30.0, meteorite.getLatitude(), 0.01); // Allowing a small margin of error for double comparison
        assertEquals(-75.0, meteorite.getLongitude(), 0.01);
        assertEquals(128, meteorite.getFall());
        assertEquals(500.0, meteorite.getMass(), 0.01);
    }



    /**
     * Test the "readDataFromFile" method to ensure successful data loading from a CSV file.
     */
    @Test
    public void testReadDataFromFile() {
        // Initialize the concrete backend implementation
        backend = new Backend();
        try {
            // Calling the method to read data from a CSV file
            backend.readDataFromFile("meteorites.csv");

            // Add assertions or checks here to verify that data has been successfully loaded

        } catch (Exception e) {
            // Ensure that no exceptions are thrown during data loading
            fail("Exception occurred: " + e.getMessage());
        }
    }


    /**
     * Test the "insertFromCSV" method to ensure successful insertion of data from a CSV file.
     */

    @Test
    public void testInsertFromCSV() {
        // Initialize the concrete backend implementation
        backend = new Backend();

        try {
            // Calling the method to insert data from a CSV file
            boolean result = backend.insertFromCSV("test_data.csv");

            // Check if insertion was successful
            assertTrue(result);

        } catch (Exception e) {
            // Ensure that no exceptions are thrown during insertion
            fail("Exception occurred: " + e.getMessage());
        }
    }


    /**
     * Test the "getMeteoritesWithMaxMass" method to retrieve meteorites with the highest mass.
     */
    @Test
    public void testGetMeteoritesWithMaxMass() {
        // Initialize the concrete backend implementation
        backend = new Backend();

        // Assuming you have populated the data structure with test data
        // (e.g., using insertFromCSV)

        // Call the method to retrieve meteorites with the highest mass
        List<Meteorite> result = backend.getMeteoritesWithMaxMass();

        // Check if the list is not empty
        assertFalse(result.isEmpty());
    }
    public void testGetMeteoritesWithinMassRange() {
        // Initialize the concrete backend implementation
        backend = new Backend();

        // Assuming you have populated the data structure with test data

        // Call the method to retrieve meteorites within a specified mass range
        List<Meteorite> result = backend.getMeteoritesWithinMassRange(100, 500);

        // Check if the list is not empty
        assertTrue(!result.isEmpty());

        // Check if all meteorites in the list have mass within the specified range
        for (Meteorite meteorite : result) {
            double mass = meteorite.getMass();
            assertTrue(mass >= 100 && mass <= 500);
        }
    }



}
