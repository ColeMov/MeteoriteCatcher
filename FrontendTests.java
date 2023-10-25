// --== CS400 Fall 2023 File Header Information ==--
// Name: Cole Movsessian
// Email: movsessian@wisc.edu
// Group: C35
// TA: Alexander Peseckis
// Lecturer: Florian Heimerl

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FrontendTests {
    @Test
    /**
     * Tests if user provides undefined input for first command line.
     * Should loop original command prompt.
     */
    public void invalidCommandTest(){
        TextUITester tester = new TextUITester("101\n4");
        Scanner scnr = new Scanner(System.in);
        BackendImplementation backend = new BackendImplementation();
        FrontendImplementation frontend = new FrontendImplementation(backend,scnr);
        frontend.startCommandLoop();

        String output = tester.checkOutput();
        if(output.contains("Invalid command: try again")){
            Assertions.assertTrue(true);
        }else{
            Assertions.fail("Test Case 1: Failed");
        }
    }

    @Test
    /**
     * Tests if loadFile command is called and file name is given
     * Should inform user of submission and run backend file call
     */
    public void loadFileTest(){
        TextUITester tester = new TextUITester("1\nmeteorites.csv\n4");
        Scanner scnr = new Scanner(System.in);
        BackendImplementation backend = new BackendImplementation();
        FrontendImplementation frontend = new FrontendImplementation(backend,scnr);

        frontend.startCommandLoop();
        String output = tester.checkOutput();
        if(output.contains("Searching for file...") && output.contains("loaded")){
            Assertions.assertTrue(true);
        }else{
            Assertions.fail("Test Case 2: Failed");
        }
    }

    @Test
    /**
     * Tests if loadAllRange command is called and invalid range is submitted
     * Should rerequest range values and inform user of invalid submission
     */
    public void invalidRangeTest(){
        TextUITester tester = new TextUITester("3\n503.00\n500.00\n500.00\n503.00\n4");
        Scanner scnr = new Scanner(System.in);
        BackendImplementation backend = new BackendImplementation();
        FrontendImplementation frontend = new FrontendImplementation(backend,scnr);

        frontend.startCommandLoop();
        String output = tester.checkOutput();
        if(output.contains("Invalid range: try again")){
            Assertions.assertTrue(true);
        }else{
            Assertions.fail("Test Case 3: Failed");
        }
    }

    @Test
    /**
     * Tests if loadAllRange command is called and range is submitted
     * Should finish execution and display results
     */
    public void rangeTest(){
        TextUITester tester = new TextUITester("3\n0.00\n0.01\n4");
        Scanner scnr = new Scanner(System.in);
        BackendImplementation backend = new BackendImplementation();
        FrontendImplementation frontend = new FrontendImplementation(backend,scnr);

        frontend.startCommandLoop();
        String output = tester.checkOutput();
        if(output.contains("Printing meteorites within range...")){
            Assertions.assertTrue(true);
        }else{
            Assertions.fail("Test Case 4: Failed");
        }
    }

    @Test
    /**
     * Tests expected output from load highest command
     * Should run program as expected with loadHighMeteorite method call
     */
    public void loadHighestTest(){
        TextUITester tester = new TextUITester("2\n4");
        Scanner scnr = new Scanner(System.in);
        BackendImplementation backend = new BackendImplementation();
        FrontendImplementation frontend = new FrontendImplementation(backend,scnr);

        frontend.startCommandLoop();
        String output = tester.checkOutput();
        if(output.contains("Printing meteorites with highest mass...")){
            Assertions.assertTrue(true);
        }else{
            Assertions.fail("Test Case 5: Failed");
        }
    }

    @Test
    /**
     * Tests expected command loop prompt submission and exits app
     * Should run as expected till exit and encounter no errors
     */
    public void exitTest(){
        TextUITester tester = new TextUITester("4");
        Scanner scnr = new Scanner(System.in);
        BackendImplementation backend = new BackendImplementation();
        FrontendImplementation frontend = new FrontendImplementation(backend,scnr);

        frontend.startCommandLoop();
        String output = tester.checkOutput();
        if(output.contains("Exiting application...")){
            Assertions.assertTrue(true);
        }else{
            Assertions.fail("Test Case 6: Failed");
        }
    }

    @Test
    /**
     * Tests backend integration of file loader if invalid file is provided
     * Should run and throw FileNotFoundException
     */
    public void fileNotFoundIntegrationTest() {
        TextUITester tester = new TextUITester("1\nunknownFile.txt\n4");
        Scanner scnr = new Scanner(System.in);
        BackendImplementation backend = new BackendImplementation();
        FrontendImplementation frontend = new FrontendImplementation(backend, scnr);

        frontend.startCommandLoop();
        String output = tester.checkOutput();
        if(output.contains("File not found")){
            Assertions.assertTrue(true);
        }else{
            Assertions.fail("Error not thrown");
        }
    }

    @Test
    /**
     * Tests backend integration to ensure meteorite data list is returned
     * Should run and output returned data
     */
    public void dataReturnedIntegrationTest(){
        TextUITester tester = new TextUITester("3\n500.00\n503.00\n4");
        Scanner scnr = new Scanner(System.in);
        BackendImplementation backend = new BackendImplementation();
        FrontendImplementation frontend = new FrontendImplementation(backend, scnr);

        frontend.startCommandLoop();
        String output = tester.checkOutput();
        if(output.contains("No results in range")){
            Assertions.fail("No data returned from backend");
        }else{
            Assertions.assertTrue(true);
        }
    }
}
