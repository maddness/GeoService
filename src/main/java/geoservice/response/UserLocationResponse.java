package geoservice.response;

/**
 * Created by Alexey Ostrikov on 26/03/2016.
 */
public class UserLocationResponse implements Response {

    private final String locationRange;
    private final int distance;
    private final double meanDiatance;

    public UserLocationResponse(String locationRange, int distance, double meanDiatance) {
        this.locationRange = locationRange;
        this.distance = distance;
        this.meanDiatance = meanDiatance;
    }

    public String getLocationRange() {
        return locationRange;
    }

    public int getDistance() {
        return distance;
    }

    public double getMeanDiatance() {
        return meanDiatance;
    }
}
