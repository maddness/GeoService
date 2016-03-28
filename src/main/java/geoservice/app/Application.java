package geoservice.app;

import com.google.common.base.Throwables;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

/**
 * Starting point of the application.
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println(Arrays.toString(args));
            System.out.println("Usage: java -jar geoservice-1.0.0.jar <path_to_cells_table> <path_to_users_table>");
            System.exit(1);
        }

        System.setProperty("geoservice.input.cells", args[0]);
        System.setProperty("geoservice.input.users", args[1]);

        try {
            SpringApplication.run(Application.class, args);
        } catch (RuntimeException e) {
            System.out.println(Throwables.getStackTraceAsString(e));
        }
    }
}
