// --== CS400 Fall 2023 File Header Information ==--
// Name: Arnav Srivastav
// Email: asrivastav32@wisc.edu
// Group: C35
// TA: Alexander Peseckis
// Lecturer: Florian Heimerl

public class MeteoriteImpl implements Meteorite {
    private String name;
    private double latitude;
    private double longitude;
    private boolean fall;
    private double mass;

    public MeteoriteImpl(String name, double latitude, double longitude, boolean fall, double mass) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.fall = fall;
        this.mass = mass;
    }

    @Override
    public int compareTo(Meteorite o) {
        // Implement comparison logic based on mass
        return Double.compare(this.mass, o.getMass());
    }

    /**
     * Get the name of the meteorite.
     *
     * @return The name of the meteorite.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Get the latitude of the meteorite's landing location.
     *
     * @return The latitude of the meteorite.
     */
    @Override
    public double getLatitude() {
        return latitude;
    }

    /**
     * Get the longitude of the meteorite's landing location.
     *
     * @return The longitude of the meteorite.
     */
    @Override
    public double getLongitude() {
        return longitude;
    }

    /**
     * Check if the meteorite fell (true) or was found (false).
     *
     * @return True if the meteorite fell, false if it was found.
     */
    @Override
    public boolean getFall() {
        return fall;
    }

    /**
     * Get the mass of the meteorite.
     *
     * @return The mass of the meteorite.
     */
    @Override
    public double getMass() {
        return mass;
    }
}
