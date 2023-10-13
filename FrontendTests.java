import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
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
        BackendPlaceholderFrontend backend = new BackendPlaceholderFrontend();
        FrontendInterface frontend = new FrontendInterface(backend,scnr);
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
        TextUITester tester = new TextUITester("1\nfakeFile.txt");
        Scanner scnr = new Scanner(System.in);
        BackendPlaceholderFrontend backend = new BackendPlaceholderFrontend();
        FrontendInterface frontend = new FrontendInterface(backend,scnr);

        frontend.startCommandLoop();
        String output = tester.checkOutput();
        if(output.contains("Searching for file...")){
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
        TextUITester tester = new TextUITester("3\n503.00\n500.00\n500.00\n503.00");
        Scanner scnr = new Scanner(System.in);
        BackendPlaceholderFrontend backend = new BackendPlaceholderFrontend();
        FrontendInterface frontend = new FrontendInterface(backend,scnr);

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
     * Tests if loadAllRange command is called and range with no meteorites is submitted
     * Should finish execution and display empty results
     */
    public void emptyRangeTest(){
        TextUITester tester = new TextUITester("3\n0.00\n0.01");
        Scanner scnr = new Scanner(System.in);
        BackendPlaceholderFrontend backend = new BackendPlaceholderFrontend();
        FrontendInterface frontend = new FrontendInterface(backend,scnr);

        frontend.startCommandLoop();
        String output = tester.checkOutput();
        if(output.contains("Printing meteorites within range...") && output.contains("No results in range")){
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
        TextUITester tester = new TextUITester("2");
        Scanner scnr = new Scanner(System.in);
        BackendPlaceholderFrontend backend = new BackendPlaceholderFrontend();
        FrontendInterface frontend = new FrontendInterface(backend,scnr);

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
        BackendPlaceholderFrontend backend = new BackendPlaceholderFrontend();
        FrontendInterface frontend = new FrontendInterface(backend,scnr);

        frontend.startCommandLoop();
        String output = tester.checkOutput();
        if(output.contains("Exiting application...")){
            Assertions.assertTrue(true);
        }else{
            Assertions.fail("Test Case 6: Failed");
        }
    }
}
