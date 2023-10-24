import java.util.Scanner;
import java.util.List;

public interface FrontendInterface {

	/**
	 * Constructor for frontend interface
	 * @param backend interface with data
	 * @param scanner object
	 */
	/*
	public FrontendInterface(BackendInterface backend, Scanner scanner) {
		this.backend = backend;
		this.scanner = scanner;
	}
	 */


	/**
	 * Begins command loop
	 */
	default void startCommandLoop(){ }

	/**
	 * Outputs readable menu
	 */
	default void displayMenu(){}

	/**
	 * Specifies and loads given file
	 */
	default void loadFile(){
	}

	/**
	 * Lists meteorites with highest mass
	 */
	default void listHighMeteorites(){
	}

	/**
	 * Takes input parameters and lists meteorites within given range
	 * @throws IllegalArgumentException if given range is invalid
	 */
	default void listAllRange() throws IllegalArgumentException{
	}

	/**
	 * Iterates through list and displays results following given command
	 */
	default void displayResults(List<Meteorite> data){
	}

	/**
	 * Terminates application processes and runs any desired closing operations
	 */
	default void exitApp() {

	}
}


