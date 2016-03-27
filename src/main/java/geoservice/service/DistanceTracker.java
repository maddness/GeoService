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
 * Service to get the information about previous and new locations of a user.
 */
public class DistanceTracker {

    private final Map<CellKey, Cell> cellsMap;
    private final Map<Integer, User> usersMap;

    public DistanceTracker(Map<CellKey, Cell> cellsMap, Map<Integer, User> usersMap) {
        this.cellsMap = cellsMap;
        this.usersMap = usersMap;
    }

    /**
     * Returns information about previous and new user locations.
     *
     * @param userId user ID
     * @param lat user latitude
     * @param lon user longitude
     * @return information about user location
     */
    public Response getLocationDetails(int userId, double lat, double lon) {
        User user = usersMap.get(userId);
        if (user == null) {
            return failure("No users with id " + userId + " were found");
        }

        Cell cell = cellsMap.get(cellKeyFor(lat, lon));
        if (cell == null) {
            return failure("No cells were found for coordinates: (" + cellKeyFor(lat, lon) + ")");
        }

        int distance = distanceBetween(lat, lon, user.getLat(), user.getLon());
        double meanDistance = cell.getMeanDistance();
        String distanceRange = distance <= meanDistance ? "рядом с меткой" : "вдали от метки";

        return success(distanceRange, distance, meanDistance);
    }

    private static Response success(String distanceRange, int distance, double meanDistance) {
        return new UserLocationResponse(distanceRange, distance, meanDistance);
    }

    private static Response failure(String message) {
        return new ErrorResponse(message);
    }
}
