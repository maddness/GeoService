package geoservice.response;

/**
 * Response with information about user location:
 * 1. distance from input coordinates to last user label,
 * 2. mean distance of a current user cell, according to input
 * 3. whether current user location is near or far away from the label.
 */
public class UserLocationResponse implements Response {

    private final String locationRange;
    private final int distance;
    private final double meanDistance;

    public UserLocationResponse(String locationRange, int distance, double meanDistance) {
        this.locationRange = locationRange;
        this.distance = distance;
        this.meanDistance = meanDistance;
    }

    public String getLocationRange() {
        return locationRange;
    }

    public int getDistance() {
        return distance;
    }

    public double getMeanDistance() {
        return meanDistance;
    }
}
