package geoservice.utils;

import geoservice.model.Cell;
import geoservice.model.User;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static geoservice.utils.InputReader.readCells;
import static geoservice.utils.InputReader.readUsers;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class InputReaderTest {

    @Test
    public void shouldProcessGridFile() {
        List<Cell> cells = readCells("src/test/resources/cells.csv");

        assertEquals(9, cells.size());
        assertEquals(10, cells.get(1).getLat());
        assertEquals(11, cells.get(1).getLon());
        assertEquals(bigDecimal(0.5), bigDecimal(cells.get(1).getMeanDistance()));
        assertEquals(0, cells.get(1).getUserCount());
    }

    @Test(expected = RuntimeException.class)
    public void shouldRejectGridFileWithMistakes() {
        readCells("src/test/resources/cells_mistake.csv");
    }


    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionIfCellsFileNotFound() {
        readCells("src/test/resources/fun_cells.csv");
    }

    @Test
    public void shouldProcessUsersFile() {
        List<User> users = readUsers("src/test/resources/users.csv");

        assertEquals(6, users.size());
        assertEquals(2, users.get(1).getId());
        assertEquals(bigDecimal(11.36), bigDecimal(users.get(1).getLat()));
        assertEquals(bigDecimal(10.8), bigDecimal(users.get(1).getLon()));
        assertNull(users.get(1).getCell());
    }

    @Test(expected = RuntimeException.class)
    public void shouldRejectUsersFileWithMistakes() {
        readCells("src/test/resources/users_mistake.csv");
    }


    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionIfUsersFileNotFound() {
        readUsers("src/test/resources/fun_users.csv");
    }

    private static BigDecimal bigDecimal(double val) {
        return BigDecimal.valueOf(val);
    }
}