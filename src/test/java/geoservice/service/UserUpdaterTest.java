package geoservice.service;

import geoservice.model.Cell;
import geoservice.model.CellKey;
import geoservice.model.User;
import geoservice.response.ErrorResponse;
import geoservice.response.UserUpdateResponse;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;
import static geoservice.model.Cell.newCell;
import static geoservice.model.User.newUser;
import static geoservice.utils.StructureBuilder.createCellsMap;
import static geoservice.utils.StructureBuilder.createUsersMap;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserUpdaterTest {

    private UserUpdater userUpdater;

    @Before
    public void setUp() throws Exception {
        List<Cell> cells = newArrayList(
                newCell(1, 1, 1),           // 2 users
                newCell(1, 2, 1),           // 0 users
                newCell(1, 3, 1));          // 1 user

        List<User> users = newArrayList(
                newUser(777, 1.5, 1.8),     // cell 1
                newUser(999, 1.0, 1.2),     // cell 1
                newUser(666, 1.5, 3.8));    // cell 3

        Map<CellKey, Cell> cellsMap = createCellsMap(cells);
        Map<Integer, User> usersMap = createUsersMap(cellsMap, users);

        userUpdater = new UserUpdater(cellsMap, usersMap);
    }

    @Test
    public void shouldAddNewUser() {
        UserUpdateResponse response = (UserUpdateResponse) userUpdater.addOrUpdateUser(520, 1.3, 2.8);

        assertThat(response.getMessage(), is("New user created with id: 520, coordinates: (1.3,2.8)"));
    }

    @Test
    public void shouldKeepUserInTheSameCell() {
        UserUpdateResponse response = (UserUpdateResponse) userUpdater.addOrUpdateUser(777, 1.1, 1.9);

        assertThat(response.getMessage(), is("Label for user 777 was updated with coordinates (1.1,1.9)"));
    }

    @Test
    public void shouldMoveUserToDifferentCell() {
        UserUpdateResponse response = (UserUpdateResponse) userUpdater.addOrUpdateUser(777, 1.1, 3.9);

        assertThat(response.getMessage(), is("Label for user 777 was updated with coordinates (1.1,3.9)"));
    }

    @Test
    public void shouldNotUpdateUserIfCellNotFound() {
        ErrorResponse response = (ErrorResponse) userUpdater.addOrUpdateUser(777, 8.1, 2.9);

        assertThat(response.getError(), is("No cells were found for coordinates: (8,2). User can't be updated"));
    }

    @Test
    public void shouldNotAddUserIfCellNotFound() {
        ErrorResponse response = (ErrorResponse) userUpdater.addOrUpdateUser(520, 2.3, 2.8);

        assertThat(response.getError(), is("No cells were found for coordinates: (2,2). User can't be created"));
    }
}