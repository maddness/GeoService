package geoservice.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Starting point of the application
 */
@SpringBootApplication
public class Application {

    private static Logger LOG = LogManager.getLogger(Application.class);

    public static void main(String[] args) {
        System.setProperty("geoservice.input.users", "/Users/maddness/IdeaProjects/GeoService/tables_generation/users.csv");
        System.setProperty("geoservice.input.cells", "/Users/maddness/IdeaProjects/GeoService/tables_generation/cells.csv");

        try {
            SpringApplication.run(Application.class, args);
        } catch (RuntimeException e) {
            LOG.error("============== Program aborted! =================\n" + e.getCause());
        }
    }
}
