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
     * @return A list of meteorites with the maximum mass.
     */
    List<Meteorite> getMeteoritesWithMaxMass();

    /**
     * Get a list of meteorites with a mass between the specified thresholds.
     * @param minMass The minimum mass threshold (inclusive).
     * @param maxMass The maximum mass threshold (inclusive).
     * @return A list of meteorites with mass within the specified range.
     */
    List<Meteorite> getMeteoritesByMassRange(double minMass, double maxMass);
}

