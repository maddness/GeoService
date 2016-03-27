package geoservice.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Starting point of the application
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
//        System.setProperty("geoservice.input.users", "/Users/maddness/IdeaProjects/GeoService/src/main/resources/users.csv");
//        System.setProperty("geoservice.input.cells", "/Users/maddness/IdeaProjects/GeoService/src/main/resources/cells.csv");

        System.setProperty("geoservice.input.users", "/Users/maddness/IdeaProjects/GeoService/tables_generation/users.csv");
        System.setProperty("geoservice.input.cells", "/Users/maddness/IdeaProjects/GeoService/tables_generation/cells.csv");

        SpringApplication.run(Application.class, args);
    }
}
