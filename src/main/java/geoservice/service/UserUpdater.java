package geoservice.service;

import geoservice.model.Cell;
import geoservice.model.CellKey;
import geoservice.model.User;
import geoservice.response.UserResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

import static geoservice.model.CellKey.cellKeyFor;
import static geoservice.model.User.newUser;
import static geoservice.response.Status.ERROR;
import static geoservice.response.Status.SUCCESS;

/**
 * Service to add new users to the system or update label coordinates of existing users.
 */
public class UserUpdater {

    private static Logger LOG = LogManager.getLogger(UserUpdater.class);

    private final Map<CellKey, Cell> cellsMap;
    private final Map<Integer, User> usersMap;

    public UserUpdater(Map<CellKey, Cell> cellsMap, Map<Integer, User> usersMap) {
        this.cellsMap = cellsMap;
        this.usersMap = usersMap;
    }

    /**
     * Public method to add new user or update the existing one
     * @param userId user ID
     * @param lat user latitude
     * @param lon user longitude
     * @return result of the operation, will be serialized as json
     */
    public UserResponse addOrUpdateUser(int userId, double lat, double lon) {
        User existingUser = usersMap.get(userId);
        if (existingUser == null) {
            return createNewUser(userId, lat, lon);
        } else {
            return updateUserCoordinates(existingUser, lat, lon);
        }
    }

    /**
     * Creates new user with defined coordinates if appropriate cell have been found.
     * If no cells for such coordinates were found, request ignored.
     */
    private UserResponse createNewUser(int userId, double lat, double lon) {
        Cell cell = cellsMap.get(cellKeyFor(lat, lon));
        if (cell == null) {
            return failure("No cells were found for coordinates (" + cellKeyFor(lat, lon) + "). User can't be created.");
        } else {
            User newUser = newUser(userId, lat, lon);
            newUser.setCell(cell);
            cell.incrementUserCount();
            usersMap.put(userId, newUser);

            return success("New user created: id: " + userId + ", coordinates: (" + lat + "," + lon + ")");
        }
    }

    /**
     * Updates label coordinates of existing user if a cell for new coordinates have been found.
     */
    private UserResponse updateUserCoordinates(User user, double lat, double lon) {
        Cell newCell = cellsMap.get(cellKeyFor(lat, lon));
        if (newCell == null) {
            return failure("No cells were found for coordinates: (" + cellKeyFor(lat, lon) + "). User can't be updated");
        }

        Cell oldCell = user.getCell();
        if (oldCell != null) {
            oldCell.decrementUserCount();
        }

        newCell.incrementUserCount();
        user.setCell(newCell);

        return success("Label for user " + user.getId() + " was updated with coordinates (" + lat + "," + lon + ")");
    }

    private static UserResponse success(String message) {
        LOG.info(message);
        return new UserResponse(message, SUCCESS);
    }

    private static UserResponse failure(String message) {
        LOG.info(message);
        return new UserResponse(message, ERROR);
    }
}




