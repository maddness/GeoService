package geoservice.utils;

import org.junit.Test;

import static geoservice.utils.DistanceCalculator.distanceBetween;
import static junit.framework.TestCase.assertEquals;

public class DistanceCalculatorTest {

    @Test
    public void shouldCalculateLongDistance() {
        assertEquals(301289, distanceBetween(
                55.75396, 37.620393,        // Moscow coordinates
                57.767961, 40.926858        // Kostroma coordinates
        ));
    }

    @Test
    public void shouldCalculateSmallDistance() {
        assertEquals(11533, distanceBetween(
                55.748188, 37.540292,        // OneFactor building location
                55.6445, 37.538802           // my home location
        ));
    }

    @Test
    public void shouldCalculateVerySmallDistance() {
        assertEquals(2, distanceBetween(
                -55.748118, 37.540692,
                -55.748138, 37.540692
        ));
    }

    @Test
    public void shouldCalculateZeroDistance() {
        assertEquals(0, distanceBetween(55.75396, 37.620393, 55.75396, 37.620393));
        assertEquals(0, distanceBetween(0, 0, 0, 0));
    }
}