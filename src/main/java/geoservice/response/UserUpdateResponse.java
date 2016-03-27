package geoservice.response;

/**
 * Response with information about result of user update or creation of a new user.
 */
public class UserUpdateResponse implements Response {

    private final String message;

    public UserUpdateResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
