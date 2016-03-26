package geoservice.app;

import geoservice.model.Cell;
import geoservice.model.CellKey;
import geoservice.model.User;
import geoservice.response.Response;
import geoservice.response.UserResponse;
import geoservice.service.GridInformer;
import geoservice.service.UserTracker;
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
 * Created by Alexey Ostrikov on 27/03/2016.
 */
@Component
public class ServiceProvider {

    private GridInformer gridInformer;
    private UserTracker userTracker;
    private UserUpdater userUpdater;

    @PostConstruct
    public void init() {
        List<Cell> cells = readCells(System.getProperty("geoservice.source.grid"));
        List<User> users = readUsers(System.getProperty("geoservice.source.users"));

        Map<CellKey, Cell> cellsMap = createCellsMap(cells);
        Map<Integer, User> usersMap = createUsersMap(cellsMap, users);

        userUpdater = new UserUpdater(cellsMap, usersMap);
        userTracker = new UserTracker(cellsMap, usersMap);
        gridInformer = new GridInformer(cellsMap);
    }

    public UserResponse addOrUpdateUser(int userId, double lat, double lon) {
        return userUpdater.addOrUpdateUser(userId, lat, lon);
    }

    public Response getLocationDetails(int userId, double lat, double lon) {
        return userTracker.getLocationDetails(userId, lat, lon);
    }

    public Response getCellUserCount(double lat, double lon) {
        return gridInformer.getCellUserCount(lat, lon);
    }

}
