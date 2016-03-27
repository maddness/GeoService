package geoservice.service;

import geoservice.model.Cell;
import geoservice.model.CellKey;
import geoservice.model.User;
import geoservice.response.ErrorResponse;
import geoservice.response.Response;
import geoservice.response.UserLocationResponse;

import java.util.Map;

import static geoservice.model.CellKey.cellKeyFor;
import static geoservice.utils.DistanceCalculator.distanceBetween;

/**
 * Service to get information about user location and distance
 */
public class DistanceTracker {

    private final Map<CellKey, Cell> cellsMap;
    private final Map<Integer, User> usersMap;

    public DistanceTracker(Map<CellKey, Cell> cellsMap, Map<Integer, User> usersMap) {
        this.cellsMap = cellsMap;
        this.usersMap = usersMap;
    }

    /**
     * Blah blah...
     *
     * @param userId
     * @param lat
     * @param lon
     * @return
     */
    public Response getLocationDetails(int userId, double lat, double lon) {
        User user = usersMap.get(userId);
        if (user == null) {
            return failure("No users with id " + userId + " were found");
        }

        Cell cell = cellsMap.get(cellKeyFor(lat, lon));
        if (cell == null) {
            return failure("No cells were found for coordinates (" + cellKeyFor(lat, lon) + ")");
        }

        int distance = distanceBetween(lat, lon, user.getLat(), user.getLon());
        double meanDistance = user.getCell().getMeanDistance();
        String distanceRange = distance <= meanDistance ? "close to the label" : "far away from the label";

        return success(distanceRange, distance, meanDistance);
    }

    private static Response success(String distanceRange, int distance, double meanDistance) {
        return new UserLocationResponse(distanceRange, distance, meanDistance);
    }

    private static Response failure(String message) {
        return new ErrorResponse(message);
    }
}
