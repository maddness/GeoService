package geoservice.response;

/**
 * Created by Alexey Ostrikov on 26/03/2016.
 */
public class UserLocationResponse implements Response {

    private final String locationRange;
    private final double distance;
    private final double meanDiatance;

    public UserLocationResponse(String locationRange, double distance, double meanDiatance) {
        this.locationRange = locationRange;
        this.distance = distance;
        this.meanDiatance = meanDiatance;
    }

    public String getLocationRange() {
        return locationRange;
    }

    public double getDistance() {
        return distance;
    }

    public double getMeanDiatance() {
        return meanDiatance;
    }
}
