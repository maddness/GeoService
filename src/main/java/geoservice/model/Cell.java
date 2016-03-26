package geoservice.model;

import static geoservice.utils.InputsChecker.validateCoordinatesForCell;
import static geoservice.utils.InputsChecker.validateMedianDistance;

/**
 * Cell object with coordinates, mean distance and user count
 */
public class Cell {
    private final int lat;
    private final int lon;
    private final double meanDistance;

    private int userCount;

    public static Cell newCell(int lat, int lon, double meanDistance) {
        validateCoordinatesForCell(lat, lon);
        validateMedianDistance(meanDistance);

        return new Cell(lat, lon, meanDistance);
    }

    private Cell(int lat, int lon, double meanDistance) {
        this.lat = lat;
        this.lon = lon;
        this.meanDistance = meanDistance;
        this.userCount = 0;
    }

    public int getLat() {
        return lat;
    }

    public int getLon() {
        return lon;
    }

    public double getMeanDistance() {
        return meanDistance;
    }

    public int getUserCount() {
        return userCount;
    }

    public void incrementUserCount() {
        userCount++;
    }

    public void decrementUserCount() {
        if (userCount > 0) {
            userCount--;
        }
    }

    @Override
    public String toString() {
        return lat + "," + lon + " -> " + userCount + " users";
    }
}
