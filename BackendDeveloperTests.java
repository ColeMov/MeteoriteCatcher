// --== CS400 Fall 2023 File Header Information ==--
// Name: Arnav Srivastav
// Email: asrivastav32@wisc.edu
// Group: C35
// TA: Alexander Peseckis
// Lecturer: Florian Heimerl

import org.junit.jupiter.api.Assertions;
import java.util.Scanner;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for the Backend Developer's implementation of the Backend interface.
 * These tests verify the functionality of methods related to reading and managing meteorite data.
 */
public class BackendDeveloperTests  {

    private BackendImplementation backend;

    /**
     * Test the accessor methods for the Meteorite class.
     */
    @Test
    public void testMeteoriteAccessors() {
        // Create a sample Meteorite instance
        Meteorite meteorite = new MeteoriteImpl("Sample Meteorite", 30.0, -75.0, true, 500.0);

        // Test accessor methods
        assertEquals("Sample Meteorite", meteorite.getName());
        assertEquals(30.0, meteorite.getLatitude(), 0.01); // Allowing a small margin of error for double comparison
        assertEquals(-75.0, meteorite.getLongitude(), 0.01);
        assertTrue(meteorite.getFall()); // Use assertTrue for boolean getters
        assertEquals(500.0, meteorite.getMass(), 0.01);
    }

    /**
     * Test reading meteorite data from a file.
     */
    @Test
    public void testReadDataFromFile() {
        BackendImplementation backend = new BackendImplementation();
        String filePath = "meteorites.csv";

        try {
            backend.readDataFromFile(filePath);

            // Check if the data has been loaded into meteoriteTree
            assertFalse(backend.getMeteoriteTree().isEmpty());
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    /**
     * Test the insertion of meteorite data from a CSV file.
     */
    @Test
    public void testInsertFromCSV() {
        BackendImplementation backend = new BackendImplementation();
        String filePath = "meteorites.csv";

        try {
            boolean result = backend.insertFromCSV(filePath);

            // Check if insertion was successful
            assertTrue(result);
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    /**
     * Test retrieving meteorites with the maximum mass.
     */
    @Test
    public void testGetMeteoritesWithMaxMass() {
        BackendImplementation backend = new BackendImplementation();
        String filePath = "meteorites.csv";

        try {
            backend.readDataFromFile(filePath);
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }


        // Call the method to retrieve meteorites with the highest mass
        List<Meteorite> result = backend.getMeteoritesWithMaxMass();

        // Check if the list is not empty
        assertFalse(result.isEmpty());

    }

    /**
     * Test retrieving meteorites within a specified mass range.
     */
    @Test
    public void testGetMeteoritesWithinMassRange() {
        BackendImplementation backend = new BackendImplementation();
        String filePath = "meteorites.csv";

        try {
            backend.readDataFromFile(filePath);
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }

        double minMass = 0.0;
        double maxMass = 100.0;
        List<Meteorite> result = backend.getMeteoritesWithinMassRange(minMass, maxMass);
        assertFalse(result.isEmpty());

        for (Meteorite meteorite : result) {
            double mass = meteorite.getMass();
            assertTrue(mass >= minMass && mass <= maxMass);
        }
    }
    /**
     * Integration test for loading data from a file through the frontend.
     * This test checks if the frontend properly calls the backend's readDataFromFile method.
     */
    @Test
    public void IntegrationTestLoadFile() {
        TextUITester tester = new TextUITester("1\nmeteorite.csv\n4"); // Provided commands: Load file, File name, Exit
        Scanner scnr = new Scanner(System.in);
        BackendImplementation backend = new BackendImplementation();
        FrontendInterface frontend = new FrontendImplementation(backend, scnr);

        frontend.startCommandLoop();

        String output = tester.checkOutput();
        if (output.contains("Searching for file...")) {
            Assertions.assertTrue(true);
        } else {
            Assertions.fail("Integration Test 1: Failed");
        }
    }

    /**
     * Integration test for listing meteorites within a specified mass range through the frontend.
     * This test checks if the frontend properly calls the backend's getMeteoritesByMassRange method.
     */
    @Test
    public void IntegrationTestListAllRange() {
        TextUITester tester = new TextUITester("3\n100.0\n200.0\n4"); // Provided commands: List range, Low threshold, High threshold, Exit
        BackendImplementation backend = new BackendImplementation();
        Scanner scnr = new Scanner(System.in);
        FrontendInterface frontend = new FrontendImplementation(backend, scnr);

        frontend.startCommandLoop();

        String output = tester.checkOutput();
        if (output.contains("Printing meteorites within range...")) {
            Assertions.assertTrue(true);
        } else {
            Assertions.fail("Integration Test 2: Failed");
        }
    }

}
