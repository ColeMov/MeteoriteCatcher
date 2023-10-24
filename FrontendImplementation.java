// --== CS400 Fall 2023 File Header Information ==--
// Name: Cole Movsessian
// Email: movsessian@wisc.edu
// Group: C35
// TA: Alexander Peseckis
// Lecturer: Florian Heimerl

import java.util.Scanner;
import java.util.List;

public class FrontendImplementation implements FrontendInterface {
    private Scanner scanner;
    private BackendInterface backend;

    /**
     * Constructor for frontend interface
     * @param backend interface with data
     * @param scanner object
     */
    public FrontendImplementation(BackendInterface backend, Scanner scanner) {
        this.backend = backend;
        this.scanner = scanner;
    }


    /**
     * Begins command loop
     */
    public void startCommandLoop(){
        String cmd;
        while(true){
            displayMenu();
            cmd = this.scanner.nextLine();
            if(cmd.equals("4")){
                exitApp();
                break;
            }else if(cmd.equals("1")){
                loadFile();
                break;
            }else if(cmd.equals("2")){
                listHighMeteorites();
                break;
            }else if(cmd.equals("3")){
                listAllRange();
                break;
            }else{
                System.out.println("Invalid command: try again\n");
            }
        }

    }

    /**
     * Outputs readable menu
     */
    public void displayMenu(){
        System.out.println("Select Command");
        System.out.println("==========================");
        System.out.println("1) Load file");
        System.out.println("2) List highest meteorites");
        System.out.println("3) List within range");
        System.out.println("4) Exit application");
    }

    /**
     * Specifies and loads given file
     */
    public void loadFile(){
        System.out.println("Enter file name:");
        String fileName = scanner.nextLine();
        System.out.println("Searching for file...");
        try{
            backend.readDataFromFile(fileName);
        }catch(Exception e){
            System.out.println("File not found");
        }
    }

    /**
     * Lists meteorites with highest mass
     */
    public void listHighMeteorites(){
        System.out.println("Printing meteorites with highest mass...");
        displayResults(backend.getMeteoritesWithMaxMass());
    }

    /**
     * Takes input parameters and lists meteorites within given range
     * @throws IllegalArgumentException if given range is invalid
     */
    public void listAllRange() throws IllegalArgumentException{
        System.out.println("Enter threshold in grams(low):");
        double low = scanner.nextDouble();
        System.out.println("Enter threshold in grams(high):");
        double high = scanner.nextDouble();

        while(low >= high){
            System.out.println("Invalid range: try again");
            System.out.println("Enter threshold in grams(low):");
            low = scanner.nextDouble();
            System.out.println("Enter threshold in grams(high):");
            high = scanner.nextDouble();
        }
        System.out.println("Printing meteorites within range...");
        displayResults(backend.getMeteoritesWithinMassRange(low,high));
    }

    /**
     * Iterates through list and displays results following given command
     */
    public void displayResults(List<Meteorite> data){
        if(data == null){
            System.out.println("No results in range");
        }else {
            for(int i = 0; i < data.size(); i++){
                System.out.println(data.get(i).toString());
                System.out.println(data.get(i).toString());
            }
        }
        System.out.println("==========================");
    }

    /**
     * Terminates application processes and runs any desired closing operations
     */
    public void exitApp() {
        System.out.println("Exiting application...");
    }
}


