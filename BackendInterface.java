<<<<<<< HEAD
import java.util.List;

/**
 * Interface for the backend that exposes the required functionality to the frontend.
 */
public interface BackendInterface {
    /**
     * Read meteorite data from a CSV file and insert it into a Red-Black Tree.
     * @param filePath The path to the CSV file containing meteorite data.
     */
    void readDataFromFile(String filePath);

    /**
     * Get a list of meteorites with the maximum mass in the data set.
=======
// --== CS400 Fall 2023 File Header Information ==--
// Name: Arnav Srivastav
// Email: asrivastav32@wisc.edu
// Group: C35
// TA: Alexander Peseckis
// Lecturer: Florian Heimerl

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface BackendInterface {
    /**
     * Read meteorite data from a file and populate the data structure.
     *
     * @param filePath The path to the data file.
     * @throws FileNotFoundException If the file is not found.
     * @throws IOException           If an I/O error occurs.
     */
    void readDataFromFile(String filePath) throws FileNotFoundException, IOException;

    /**
     * Insert meteorite data from a CSV file into the data structure.
     *
     * @param fileName The name of the CSV file.
     * @return True if insertion is successful, false otherwise.
     * @throws IOException If an I/O error occurs.
     */
    boolean insertFromCSV(String fileName) throws IOException;

    /**
     * Get meteorites with the maximum mass.
     *
>>>>>>> backend
     * @return A list of meteorites with the maximum mass.
     */
    List<Meteorite> getMeteoritesWithMaxMass();

    /**
<<<<<<< HEAD
     * Get a list of meteorites with a mass between the specified thresholds.
     * @param minMass The minimum mass threshold (inclusive).
     * @param maxMass The maximum mass threshold (inclusive).
     * @return A list of meteorites with mass within the specified range.
     */
    List<Meteorite> getMeteoritesByMassRange(double minMass, double maxMass);
}

=======
     * Get meteorites within a specified mass range.
     *
     * @param minMass The lower bound of the mass range.
     * @param maxMass The upper bound of the mass range.
     * @return A list of meteorites within the specified mass range.
     */
List<Meteorite> getMeteoritesWithinMassRange(double minMass, double maxMass);
}
>>>>>>> backend
