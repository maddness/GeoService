package geoservice.service;

import geoservice.model.Cell;
import geoservice.model.CellKey;
import geoservice.model.User;
import geoservice.response.ErrorResponse;
import geoservice.response.UserLocationResponse;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;
import static geoservice.model.Cell.newCell;
import static geoservice.model.User.newUser;
import static geoservice.utils.StructureBuilder.createCellsMap;
import static geoservice.utils.StructureBuilder.createUsersMap;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class DistanceTrackerTest {

    private DistanceTracker distanceTracker;

    @Before
    public void setUp() {
        List<Cell> cells = newArrayList(
                newCell(1, 1, 100060),      // 1 user
                newCell(1, 2, 100000),      // 0 users
                newCell(1, 3, 99904));      // 1 user

        List<User> users = newArrayList(
                newUser(777, 1.5, 1.8),     // cell 1
                newUser(666, 1.5, 3.8));    // cell 3

        Map<CellKey, Cell> cellsMap = createCellsMap(cells);
        Map<Integer, User> usersMap = createUsersMap(cellsMap, users);

        distanceTracker = new DistanceTracker(cellsMap, usersMap);
    }

    @Test
    public void shouldReturnErrorIfNoSuchUserFound() {
        ErrorResponse response = (ErrorResponse) distanceTracker.getLocationDetails(333, 1, 3);

        assertThat(response.getError(), is("No users with id 333 were found"));
    }

    @Test
    public void shouldReturnErrorIfNoSuchCellFound() {
        ErrorResponse response = (ErrorResponse) distanceTracker.getLocationDetails(777, 10, 30);

        assertThat(response.getError(), is("No cells were found for coordinates: (10,30)"));
    }

    @Test
    public void shouldGenerateCorrectResponseFarAway() {
        UserLocationResponse response = (UserLocationResponse) distanceTracker.getLocationDetails(777, 1, 3);

        assertEquals(bigDecimal(99904), bigDecimal(response.getMeanDistance()));
        assertEquals(bigDecimal(144564), bigDecimal(response.getDistance()));
        assertEquals("вдали от метки", response.getLocationRange());
    }

    @Test
    public void shouldGenerateCorrectResponseNear() {
        UserLocationResponse response = (UserLocationResponse) distanceTracker.getLocationDetails(777, 1.1, 2.3);

        assertEquals(bigDecimal(100000), bigDecimal(response.getMeanDistance()));
        assertEquals(bigDecimal(71208), bigDecimal(response.getDistance()));
        assertEquals("рядом с меткой", response.getLocationRange());
    }

    private static BigDecimal bigDecimal(double val) {
        return BigDecimal.valueOf(val);
    }
}