package geoservice.app;

import geoservice.response.ErrorResponse;
import geoservice.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller to handle HTTP requests
 */
@RestController
public class Controller {

    private final ServiceProvider serviceProvider;

    @Autowired
    public Controller(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    @RequestMapping("/greeting")
    public Response greeting(@RequestParam(value="name", defaultValue="World") String name) {
//        return serviceProvider.addOrUpdateUser(200, 34, 53);
        return new ErrorResponse("Hohoho!");
    }

}
