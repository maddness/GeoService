package geoservice.app;

import geoservice.response.UserResponse;
import geoservice.service.GridInformer;
import geoservice.service.UserTracker;
import geoservice.service.UserUpdater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static geoservice.response.Status.SUCCESS;

/**
 * Created by Alexey Ostrikov on 25/03/2016.
 */

@RestController
public class Controller {

    @Autowired
    private GridInformer gridInformer;

    @Autowired
    private UserTracker userTracker;

    @Autowired
    private UserUpdater userUpdater;


    @RequestMapping("/greeting")
    public UserResponse greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new UserResponse("lala", SUCCESS);
    }

}
