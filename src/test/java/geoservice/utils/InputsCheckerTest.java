package geoservice.utils;

import org.junit.Test;

import static geoservice.utils.InputsChecker.validateCoordinatesForCell;
import static geoservice.utils.InputsChecker.validateCoordinatesForUser;
import static geoservice.utils.InputsChecker.validateMedianDistance;

public class InputsCheckerTest {

    @Test
    public void shouldAcceptValidUserCoordinates() {
        validateCoordinatesForUser(123, 34, 123);
    }

    @Test
    public void shouldAcceptValidCellCoordinates() {
        validateCoordinatesForCell(-34, -123);
    }

    @Test
    public void shouldAcceptValidMedianDistance() {
        validateMedianDistance(200);
    }

    @Test(expected = NumberFormatException.class)
    public void shouldNotAcceptInvalidMedianDistance() {
        validateMedianDistance(-32);
    }

    @Test(expected = NumberFormatException.class)
    public void shouldNotAcceptInvalidUserLatitude() {
        validateCoordinatesForUser(123, 180, 123);
    }

    @Test(expected = NumberFormatException.class)
    public void shouldNotAcceptInvalidUserLatitude2() {
        validateCoordinatesForUser(123, -234, 123);
    }

    @Test(expected = NumberFormatException.class)
    public void shouldNotAcceptInvalidUserLongitude() {
        validateCoordinatesForUser(123, 24, 200);
    }

    @Test(expected = NumberFormatException.class)
    public void shouldNotAcceptInvalidUserLongitude2() {
        validateCoordinatesForUser(123, 24, -300);
    }

    @Test(expected = NumberFormatException.class)
    public void shouldNotAcceptInvalidCellLatitude() {
        validateCoordinatesForCell(180, 123);
    }

    @Test(expected = NumberFormatException.class)
    public void shouldNotAcceptInvalidCellLatitude2() {
        validateCoordinatesForCell(-234, 123);
    }

    @Test(expected = NumberFormatException.class)
    public void shouldNotAcceptInvalidCellLongitude() {
        validateCoordinatesForCell(24, 200);
    }

    @Test(expected = NumberFormatException.class)
    public void shouldNotAcceptInvalidCellLongitude2() {
        validateCoordinatesForCell(24, -300);
    }
}