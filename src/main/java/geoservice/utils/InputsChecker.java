package geoservice.utils;

/**
 * Validation methods to avoid creating objects with incorrect coordinates and median distance.
 */
public class InputsChecker {

    public static void validateCoordinatesForUser(int userId, double lat, double lon) {
        if (lat < -90 || lat > 90) {
            throw new NumberFormatException("Latitude " + lat + " is not in range [-90, 90] for user " + userId);
        } else if (lon < -180 || lon > 180) {
            throw new NumberFormatException("Longitude " + lon + " is not in range [-180, 180] for user " + userId);
        }
    }

    public static void validateCoordinatesForCell(double lat, double lon) {
        if (lat < -90 || lat > 90) {
            throw new NumberFormatException("Cell latitude " + lat + " is not in range [-90, 90]");
        } else if (lon < -180 || lon > 180) {
            throw new NumberFormatException("Cell longitude " + lon + " is not in range [-180, 180]");
        }
    }

    public static void validateMedianDistance(double medianDistance) {
        if (medianDistance < 0) {
            throw new NumberFormatException("Median distance " + medianDistance + " can't be less than zero");
        }
    }
}
