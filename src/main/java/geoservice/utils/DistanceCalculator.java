package geoservice.utils;

import static java.lang.Math.*;

public class DistanceCalculator {
    /**
     * Not the most precise way to calculate distance between GPS coordinates,
     * but I can't argue with technical description :-)
     *
     * Maybe you even have some tests to verify results of this formula.
     */
    public static double calcDistance(double latA, double lonA, double latB, double lonB) {
        // translates degrees to radians
        latA = latA * Math.PI / 180;
        lonA = lonA * Math.PI / 180;
        latB = latB * Math.PI / 180;
        lonB = lonB * Math.PI / 180;

        int earthRadius = 6372795;

        double lonDelta = abs(lonA - lonB);
        double result = acos(sin(latA) * sin(latB) + cos(latA) * cos(latB) * cos(lonDelta));

        return result * earthRadius;
    }
}
