import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BackendImplementation implements BackendInterface {
    private IterableMultiKeyRBT<Meteorite> meteoriteTree = new IterableMultiKeyRBT<>();

    public IterableMultiKeyRBT<Meteorite> getMeteoriteTree() {
        return meteoriteTree;
    }

    /**
     * Read meteorite data from a file and populate the meteorite tree.
     *
     * @param filePath The path to the data file.
     * @throws FileNotFoundException If the file is not found.
     * @throws IOException           If an I/O error occurs.
     */
    @Override
    public void readDataFromFile(String filePath) throws FileNotFoundException, IOException {
        // Check if the file exists
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException("File not found: " + filePath);
        }

        // Clear the existing data
        meteoriteTree.clear();

        // Read data from the file and populate meteoriteTree
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;

        // Skip the header line
        reader.readLine();

        while ((line = reader.readLine()) != null) {
            // Split the line into fields
            String[] datas = line.split("\"");
            String[] data = datas[0].split(",");
            String name = data[0];

            // Parse latitude and longitude
            double latitude = 0.0;
            double longitude = 0.0;
            try {
                String[] latLon = datas[1].substring(1, datas[1].length() - 1).split(",");
                latitude = Double.parseDouble(latLon[0]);
                longitude = Double.parseDouble(latLon[1]);
            } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
                continue; // Skip invalid entries
            }

            // Parse fall
            boolean fall = data[5].equals("\"Fell\"");

            // Parse mass
            double mass = 0.0;
            try {
                mass = Double.parseDouble(data[4]);
            } catch (NumberFormatException e) {
                continue; // Skip invalid entries
            }

            // Insert the meteorite into the tree
            meteoriteTree.insertSingleKey(new MeteoriteImpl(name, latitude, longitude, fall, mass));
        }

        reader.close();
    }

    /**
     * Insert meteorite data from a CSV file into the meteorite tree.
     *
     * @param fileName The name of the CSV file.
     * @return True if insertion is successful, false otherwise.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public boolean insertFromCSV(String fileName) throws IOException {
        // Implement inserting data from a CSV file into the meteoriteTree
        // You can reuse the readDataFromFile method.
        try {
            readDataFromFile(fileName);
            return true;
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            System.err.println("An error occurred during insertion: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Get meteorites with the maximum mass.
     *
     * @return A list of meteorites with the maximum mass.
     */
    @Override
    public List<Meteorite> getMeteoritesWithMaxMass() {
        if (meteoriteTree.isEmpty()) {
            return new ArrayList<>();
        }

        double maxMass = Double.MIN_VALUE; // Initialize maxMass to the smallest possible value
        List<Meteorite> meteorites = new ArrayList<>();

        for (Meteorite meteorite : meteoriteTree) {
            double mass = meteorite.getMass();
            if (mass > maxMass) {
                maxMass = mass;
                meteorites.clear(); // Clear the list because a new maximum is found
                meteorites.add(meteorite);
            } else if (mass == maxMass) {
                meteorites.add(meteorite); // Add to the list if mass is equal to the current maximum
            }
        }

        return meteorites;
    }

    /**
     * Get meteorites within a specified mass range.
     *
     * @param minMass The lower bound of the mass range.
     * @param maxMass The upper bound of the mass range.
     * @return A list of meteorites within the specified mass range.
     */
    @Override
    public List<Meteorite> getMeteoritesWithinMassRange(double minMass, double maxMass) {
        // Implement getting meteorites within a specific mass range from the meteoriteTree
        List<Meteorite> meteorites = new ArrayList();

        for (Meteorite meteorite : meteoriteTree) {
            double mass = meteorite.getMass();

            if (mass >= minMass && mass <= maxMass) {
                meteorites.add(meteorite);
            }
        }

        return meteorites;
    }
    public static void main(String[] args) {
        BackendImplementation backend = new BackendImplementation();
        Scanner scnr = new Scanner(System.in);
        FrontendImplementation frontend = new FrontendImplementation(backend,scnr); // Replace 'Frontend' with the actual frontend class name

        // Start the main command loop of the frontend
        frontend.startCommandLoop();
    }
}
