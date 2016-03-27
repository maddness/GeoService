package geoservice.utils;

import geoservice.model.Cell;
import geoservice.model.CellKey;
import geoservice.model.User;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static geoservice.model.Cell.newCell;
import static geoservice.model.CellKey.cellKeyFor;
import static geoservice.model.User.newUser;
import static geoservice.utils.StructureBuilder.createCellsMap;
import static geoservice.utils.StructureBuilder.createUsersMap;
import static org.junit.Assert.assertEquals;

public class StructureBuilderTest {

    @Test(expected = RuntimeException.class)
    public void shouldNotAllowDuplicatedCells() {
        List<Cell> cells = newArrayList(
                newCell(1, 1, 1),
                newCell(1, 2, 1),
                newCell(1, 1, 1));

        createCellsMap(cells);
    }

    @Test
    public void shouldCreateCellMap() {
        List<Cell> cells = newArrayList(
                newCell(1, 1, 1),
                newCell(2, 2, 1),
                newCell(3, 3, 1));

        assertEquals(3, createCellsMap(cells).size());
    }

    @Test(expected = RuntimeException.class)
    public void shouldNotAllowDuplicatedUsers() {
        Map<CellKey, Cell> cells = newHashMap();
        cells.put(cellKeyFor(1, 1), newCell(1, 1, 1));

        List<User> users = newArrayList(
                newUser(777, 1.5, 1.8),
                newUser(999, 1.0, 1.2),
                newUser(777, 1.5, 1.2));

        createUsersMap(cells, users);
    }

    @Test
    public void shouldPopulateCellsWithUsers() {
        Map<CellKey, Cell> cells = newHashMap();
        cells.put(cellKeyFor(1, 1), newCell(1, 1, 1));
        cells.put(cellKeyFor(1, 2), newCell(1, 2, 1));

        List<User> users = newArrayList(
                newUser(771, 1.5, 2.8),
                newUser(772, 1.9, 1.2));

        assertEquals(2, createUsersMap(cells, users).size());
    }

    @Test
    public void shouldSkipUsersOutsideOfTheGrid() {
        Map<CellKey, Cell> cells = newHashMap();
        cells.put(cellKeyFor(1, 1), newCell(1, 1, 1));
        cells.put(cellKeyFor(1, 2), newCell(1, 2, 1));
        cells.put(cellKeyFor(1, 3), newCell(1, 3, 1));

        List<User> users = newArrayList(
                newUser(771, 1.5, 2.8),
                newUser(772, 1.9, 1.2),
                newUser(773, 1.9, 3.2),
                newUser(774, 6.9, 1.2));

        assertEquals(3, createUsersMap(cells, users).size());
    }
}