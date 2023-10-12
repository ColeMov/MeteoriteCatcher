import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.util.Scanner;

public class FrontendTests {
    @Test
    /**
     * Tests if user provides undefined input for first command line.
     * Should loop original command prompt.
     */
    public void testCase1(){
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
     * Tests if loadFile command is called and invalid file name is given
     * Should rerequest fileName and inform user of invalid submission
     */
    public void testCase2(){
        TextUITester tester = new TextUITester("1\nfakeFile.txt");
        Scanner scnr = new Scanner(System.in);
        BackendPlaceholderFrontend backend = new BackendPlaceholderFrontend();
        FrontendInterface frontend = new FrontendInterface(backend,scnr);

        frontend.startCommandLoop();
        String output = tester.checkOutput();
        if(output.contains("File not found")){
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
    public void testCase3(){
        TextUITester tester = new TextUITester("3\n300.00\n299.00");
        Scanner scnr = new Scanner(System.in);
        BackendPlaceholderFrontend backend = new BackendPlaceholderFrontend();
        FrontendInterface frontend = new FrontendInterface(backend,scnr);
        try{
            frontend.startCommandLoop();
            String output = tester.checkOutput();
            Assertions.fail("Test Case 3: Failed");
        }catch(IllegalArgumentException e){
            Assertions.assertTrue(true);
        }
        Assertions.fail("Test Case 3: Failed");
    }

    @Test
    /**
     * Tests if loadAllRange command is called and range with no meteorites is submitted
     * Should finish execution and display empty results
     */
    public void testCase4(){
        TextUITester tester = new TextUITester("3\n30.00\n30.01");
        Scanner scnr = new Scanner(System.in);
        BackendPlaceholderFrontend backend = new BackendPlaceholderFrontend();
        FrontendInterface frontend = new FrontendInterface(backend,scnr);

        frontend.startCommandLoop();
        String output = tester.checkOutput();
        // add specific output after finishing displayResults()
        if(output.contains("Printing meteorite results within range...")){
            Assertions.assertTrue(true);
        }else{
            Assertions.fail("Test Case 4: Failed");
        }
    }

    /**
     * Tests expected command loop prompt submission and exits app
     * Should run as expected till exit and encounter no errors
     */
    @Test
    public void testCase5(){
        TextUITester tester = new TextUITester("4");
        Scanner scnr = new Scanner(System.in);
        BackendPlaceholderFrontend backend = new BackendPlaceholderFrontend();
        FrontendInterface frontend = new FrontendInterface(backend,scnr);

        frontend.startCommandLoop();
        String output = tester.checkOutput();
        if(output.contains("Exiting application...")){
            Assertions.assertTrue(true);
        }else{
            Assertions.fail("Test Case 5: Failed");
        }
    }
}
