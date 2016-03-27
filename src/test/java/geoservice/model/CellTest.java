package geoservice.model;

import org.junit.Test;

public class CellTest {

    @Test(expected = NumberFormatException.class)
    public void cantCreateCellWithInvalidCoordinates() {
        Cell.newCell(890, 890, 55);
    }

    @Test(expected = NumberFormatException.class)
    public void cantCreateCellWithNegativeMedian() {
        Cell.newCell(23, 24, -55);
    }
}