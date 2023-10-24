import java.util.List;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface Backend {

    /**
     * Reads meteorite data from a file and initializes the backend.
     *
     * @param fileName The name of the CSV file to read data from.
     */
    // public Backend(String fileName);

    /**
     * Reads meteorite data from a CSV file and populates the Red-Black Tree.
     *
     * @param filePath The path to the CSV file containing meteorite data.
     */

    void readDataFromFile(String filePath) throws FileNotFoundException, IOException;


    /**
     * Inserts meteorite data from a CSV file into a Red-Black Tree.
     *
     * @param fileName The name of the CSV file containing meteorite data.
     * @return true if the insertion was successful, false otherwise.
     * @throws IOException if there's an issue reading the CSV file.
     */

    /**
     * Retrieves a list of meteorites with the highest mass in the data set.
     *
     * @return A list of meteorites with the highest mass.
     */
    boolean insertFromCSV(String fileName) throws IOException;

    List<Meteorite> getMeteoritesWithMaxMass();

    /**
     * Retrieves a list of meteorites within a specified mass range.
     *
     * @param minMass The minimum mass in grams.
     * @param maxMass The maximum mass in grams.
     * @return A list of meteorites within the specified mass range.
     */
    List<Meteorite> getMeteoritesWithinMassRange(double minMass, double maxMass);
}