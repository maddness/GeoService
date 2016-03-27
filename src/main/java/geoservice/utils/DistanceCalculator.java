package geoservice.utils;

import java.math.BigDecimal;

import static java.lang.Double.isInfinite;
import static java.lang.Double.isNaN;
import static java.lang.Math.*;

public class DistanceCalculator {

    private static final int EARTH_RADIUS_METERS = 6372795;

    /**
     * Calculates distance in meters between two GPS coordinates.
     * Not the most precise way to calculate distance, but I can't argue with technical description :-)
     *
     * Maybe you even have some tests to verify results of this formula.
     *
     * @throws ArithmeticException if result is not a number
     */
    public static int distanceBetween(double latA, double lonA, double latB, double lonB) {
        // translates degrees to radians
        latA = latA * Math.PI / 180;
        lonA = lonA * Math.PI / 180;
        latB = latB * Math.PI / 180;
        lonB = lonB * Math.PI / 180;

        double lonDelta = abs(lonA - lonB);
        double result = acos(sin(latA) * sin(latB) + cos(latA) * cos(latB) * cos(lonDelta));

        if (isNaN(result) || isInfinite(result)) {
            throw new ArithmeticException("Error in distance calculation, try different values of coordinates");
        }

        return (int) (result * EARTH_RADIUS_METERS);
    }

    private static BigDecimal bd(double val) {
        return BigDecimal.valueOf(val);
    }
}
