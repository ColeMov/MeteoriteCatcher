<<<<<<< HEAD
public class Meteorite {
=======
// --== CS400 Fall 2023 File Header Information ==--
// Name: Arnav Srivastav
// Email: asrivastav32@wisc.edu
// Group: C35
// TA: Alexander Peseckis
// Lecturer: Florian Heimerl

public interface Meteorite extends Comparable<Meteorite> {
    /**
     * Get the name of the meteorite.
     *
     * @return The name of the meteorite.
     */
    String getName();

    /**
     * Get the latitude of the meteorite's landing location.
     *
     * @return The latitude of the meteorite.
     */
    double getLatitude();

    /**
     * Get the longitude of the meteorite's landing location.
     *
     * @return The longitude of the meteorite.
     */
    double getLongitude();

    /**
     * Check if the meteorite fell (true) or was found (false).
     *
     * @return True if the meteorite fell, false if it was found.
     */
    boolean getFall();

    /**
     * Get the mass of the meteorite.
     *
     * @return The mass of the meteorite.
     */
    double getMass();
>>>>>>> backend
}
