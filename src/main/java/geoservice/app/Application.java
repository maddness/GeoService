package geoservice.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by Alexey Ostrikov on 25/03/2016.
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        System.setProperty("geoservice.source.users", "/Users/maddness/IdeaProjects/GeoService/src/main/resources/users.csv");
        System.setProperty("geoservice.source.grid", "/Users/maddness/IdeaProjects/GeoService/src/main/resources/cells.csv");

        SpringApplication.run(Application.class, args);
    }
}
