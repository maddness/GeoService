package geoservice.model;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static geoservice.model.CellKey.cellKeyFor;
import static org.junit.Assert.assertTrue;

/**
 * Created by Alexey Ostrikov on 26/03/2016.
 */
public class CellKeyTest {

    @Test(expected = NumberFormatException.class)
    public void cantCreateKeyWithInvalidCoordinates() {
        CellKey.cellKeyFor(34, 890);
    }

    @Test
    public void canUseInHashTables() {
        Set<CellKey> set = new HashSet<>();
        set.add(cellKeyFor(34, 89));

        assertTrue(set.contains(cellKeyFor(34, 89)));
    }
}