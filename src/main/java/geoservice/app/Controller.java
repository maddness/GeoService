package geoservice.app;

import geoservice.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Alexey Ostrikov on 25/03/2016.
 */

@RestController
public class Controller {

    private final ServiceProvider serviceProvider;

    @Autowired
    public Controller(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    @RequestMapping("/greeting")
    public UserResponse greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return serviceProvider.addOrUpdateUser(200, 34, 53);
    }

}
