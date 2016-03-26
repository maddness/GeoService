package geoservice.utils;

import org.junit.Test;

import static geoservice.utils.DistanceCalculator.calcDistance;
import static java.math.BigDecimal.valueOf;

/**
 * Created by Alexey Ostrikov on 26/03/2016.
 */
public class DistanceCalculatorTest {

    @Test
    public void getDistance() {
        System.out.println(valueOf(calcDistance(
                55.75396, 37.620393,        //
                57.767961, 40.926858        //
        )));
    }
}