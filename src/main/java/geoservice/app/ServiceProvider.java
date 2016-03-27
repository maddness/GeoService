package geoservice.app;

import geoservice.model.Cell;
import geoservice.model.CellKey;
import geoservice.model.User;
import geoservice.response.Response;
import geoservice.service.GridInformer;
import geoservice.service.DistanceTracker;
import geoservice.service.UserUpdater;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

import static geoservice.utils.InputReader.readCells;
import static geoservice.utils.InputReader.readUsers;
import static geoservice.utils.StructureBuilder.createCellsMap;
import static geoservice.utils.StructureBuilder.createUsersMap;

/**
 * Provider of all GeoService components
 */
@Component
public class ServiceProvider {

    private GridInformer gridInformer;
    private DistanceTracker distanceTracker;
    private UserUpdater userUpdater;

    @PostConstruct
    public void init() {
        List<Cell> cells = readCells(System.getProperty("geoservice.input.cells"));
        List<User> users = readUsers(System.getProperty("geoservice.input.users"));

        Map<CellKey, Cell> cellsMap = createCellsMap(cells);
        Map<Integer, User> usersMap = createUsersMap(cellsMap, users);

        userUpdater = new UserUpdater(cellsMap, usersMap);
        distanceTracker = new DistanceTracker(cellsMap, usersMap);
        gridInformer = new GridInformer(cellsMap);
    }

    public Response addOrUpdateUser(int userId, double lat, double lon) {
        return userUpdater.addOrUpdateUser(userId, lat, lon);
    }

    public Response getLocationDetails(int userId, double lat, double lon) {
        return distanceTracker.getLocationDetails(userId, lat, lon);
    }

    public Response getCellUserCount(double lat, double lon) {
        return gridInformer.getCellUserCount(lat, lon);
    }

}
