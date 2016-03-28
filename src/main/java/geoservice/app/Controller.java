package geoservice.app;

import geoservice.response.ErrorResponse;
import geoservice.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Main controller to handle HTTP requests.
 */
@RestController
public class Controller {

    private final ServiceProvider serviceProvider;

    @Autowired
    public Controller(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }


    /**
     * Template http://localhost:8080/cell_info?lat=12&lon=11
     */
    @RequestMapping("/cell_info")
    public Response cellInfo(@RequestParam(value = "lat") double lat,
                             @RequestParam(value = "lon") double lon) {
        try {
            return serviceProvider.getCellUserCount(lat, lon);
        } catch (Exception e) {
            return new ErrorResponse(e.getMessage());
        }
    }

    /**
     * Template http://localhost:8080/update_user?user_id=123&lat=2&lon=1
     */
    @RequestMapping("/update_user")
    public Response update_user(@RequestParam(value = "user_id") int userId,
                                @RequestParam(value = "lat") double lat,
                                @RequestParam(value = "lon") double lon) {
        try {
            return serviceProvider.addOrUpdateUser(userId, lat, lon);
        } catch (Exception e) {
            return new ErrorResponse(e.getMessage());
        }
    }

    /**
     * Template http://localhost:8080/location?user_id=123&lat=2&lon=1
     */
    @RequestMapping("/location")
    public Response userLocation(@RequestParam(value = "user_id") int userId,
                                 @RequestParam(value = "lat") double lat,
                                 @RequestParam(value = "lon") double lon) {
        try {
            return serviceProvider.getLocationDetails(userId, lat, lon);
        } catch (Exception e) {
            return new ErrorResponse(e.getMessage());
        }
    }
}
