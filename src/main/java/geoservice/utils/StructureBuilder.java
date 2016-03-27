package geoservice.utils;

import geoservice.model.Cell;
import geoservice.model.CellKey;
import geoservice.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static geoservice.model.CellKey.cellKeyFor;

/**
 * Service to build structures for users and cells storage with the ability of effective retrieval.
 */
public class StructureBuilder {

    private static Logger LOG = LogManager.getLogger(StructureBuilder.class);

    /**
     * Builds map of cells for effective search and updates.
     *
     * @param cells list of cell objects from the text file
     * @return HasMap of cells with pair of coordinates as a key
     * @throws RuntimeException if cells with duplicated coordinates found
     */
    public static Map<CellKey, Cell> createCellsMap(List<Cell> cells) {
        Map<CellKey, Cell> cellsMap = new HashMap<>(1000);
        for (Cell cell : cells) {
            CellKey key = cellKeyFor(cell.getLat(), cell.getLon());
            if (!cellsMap.containsKey(key)) {
                cellsMap.put(key, cell);
            } else {
                throw new RuntimeException("Cell with duplicated coordinates " +
                        "(" + cell.getLat() + "," + cell.getLon() + ") found. Primary key violation.");
            }
        }
        LOG.info(cellsMap.size() +  " cells were loaded");
        return cellsMap;
    }

    /**
     * Builds a map of users for quick search and updates.
     *
     * @param cells map of cells, which has to be built at first
     * @param users list of users from the text file
     * @return HashMap of users with user id as a key
     * @throws RuntimeException if users with duplicated IDs found
     */
    public static Map<Integer, User> createUsersMap(Map<CellKey, Cell> cells, List<User> users) {
        Map<Integer, User> usersMap = new HashMap<>(100000);
        for (User user : users) {
            if (!usersMap.containsKey(user.getId())) {
                CellKey cellKey = cellKeyFor(user.getLat(), user.getLon());
                Cell cell = cells.get(cellKey);
                if (cell != null) {
                    usersMap.put(user.getId(), user);
                    cell.incrementUserCount();
                    user.setCell(cell);
                } else {
                    LOG.warn("Cell for user " + user.getId() + " not found. User Ignored.");
                }
            } else {
                throw new RuntimeException("User with duplicated ID (" + user.getId() + ") found. " +
                        "Primary key violation.");
            }
        }
        LOG.info(usersMap.size() + " users were loaded");
        return usersMap;
    }
}
