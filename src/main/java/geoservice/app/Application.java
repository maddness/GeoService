package geoservice.app;

import geoservice.model.Cell;
import geoservice.model.CellKey;
import geoservice.model.User;
import geoservice.service.GridInformer;
import geoservice.service.UserTracker;
import geoservice.service.UserUpdater;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Map;

import static geoservice.utils.InputReader.readCells;
import static geoservice.utils.InputReader.readUsers;
import static geoservice.utils.StructureBuilder.createCellsMap;
import static geoservice.utils.StructureBuilder.createUsersMap;

/**
 * Created by Alexey Ostrikov on 25/03/2016.
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        List<Cell> cells = readCells("src/main/resources/cells.csv");
        List<User> users = readUsers("src/main/resources/users.csv");

        Map<CellKey, Cell> cellsMap = createCellsMap(cells);
        Map<Integer, User> usersMap = createUsersMap(cellsMap, users);

        UserUpdater updater = new UserUpdater(cellsMap, usersMap);
        UserTracker tracker = new UserTracker(cellsMap, usersMap);
        GridInformer provider = new GridInformer(cellsMap);

        SpringApplication.run(Application.class, args);
    }
}
