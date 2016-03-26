package geoservice.service;

import geoservice.model.Cell;
import geoservice.model.CellKey;
import geoservice.model.User;
import geoservice.response.ErrorResponse;
import geoservice.response.Response;
import geoservice.response.UserLocationResponse;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Map;

import static geoservice.model.CellKey.cellKeyFor;
import static geoservice.utils.DistanceCalculator.calcDistance;

/**
 * Created by Alexey Ostrikov on 26/03/2016.
 */
public class UserTracker {
    private static Logger LOG = LogManager.getLogger(UserUpdater.class);

    private final Map<CellKey, Cell> cellsMap;
    private final Map<Integer, User> usersMap;

    public UserTracker(Map<CellKey, Cell> cellsMap, Map<Integer, User> usersMap) {
        this.cellsMap = cellsMap;
        this.usersMap = usersMap;
    }

    public Response getLocationDetails(int userId, double lat, double lon) {
        User user = usersMap.get(userId);
        if (user == null) {
            return failure("No users with id " + userId + " were found");
        }

        Cell cell = cellsMap.get(cellKeyFor(lat, lon));
        if (cell == null) {
            return failure("No cells were found for coordinates (" + cellKeyFor(lat, lon) + ")");
        }

        double distance = calcDistance(lat, lon, user.getLat(), user.getLon());
        double meanDistance = user.getCell().getMeanDistance();
        String distanceRange = distance <= meanDistance ? "close to the label" : "far away from the label";

        return new UserLocationResponse(distanceRange, distance, meanDistance);
    }

    private static Response failure(String message) {
        LOG.info(message);
        return new ErrorResponse(message);
    }
}
