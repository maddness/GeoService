package geoservice.service;


import geoservice.model.Cell;
import geoservice.model.CellKey;
import geoservice.response.ErrorResponse;
import geoservice.response.Response;
import geoservice.response.UserCountResponse;

import java.util.Map;

import static geoservice.model.CellKey.cellKeyFor;

/**
 * Service to check user count for a particular cell.
 */
public class GridInformer {

    private final Map<CellKey, Cell> cellsMap;

    public GridInformer(Map<CellKey, Cell> cellsMap) {
        this.cellsMap = cellsMap;
    }

    /**
     * Returns number of users located inside the cell.
     *
     * @param lat latitude of point inside a cell
     * @param lon longitude of point inside a cell
     * @return number of users located inside the cell
     * @throws RuntimeException if no cells for coordinates were found
     */
    public Response getCellUserCount(double lat, double lon) {
        Cell cell = cellsMap.get(cellKeyFor(lat, lon));
        if (cell == null) {
            return new ErrorResponse("No cells were found for coordinates (" + lat + "," + lon + ")");
        } else {
            return new UserCountResponse(cell.getUserCount());
        }
    }
}
