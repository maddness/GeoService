package geoservice.model;

import static geoservice.utils.InputsChecker.validateCoordinatesForUser;

/**
 * User object with id, coordinates and current cell.
 */
public class User {
    private final int id;

    private double lat;
    private double lon;
    private Cell cell;

    public static User newUser(int id, double lat, double lon) {
        validateCoordinatesForUser(id, lat, lon);
        return new User(id, lat, lon);
    }

    private User(int id, double lat, double lon) {
        this.id = id;
        this.lat = lat;
        this.lon = lon;
    }

    public int getId() {
        return id;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cellKey) {
        this.cell = cellKey;
    }

    @Override
    public String toString() {
        return "(" +
                "id=" + id +
                ", lat=" + lat +
                ", lon=" + lon +
                ", cell=" + cell + ')';
    }
}
