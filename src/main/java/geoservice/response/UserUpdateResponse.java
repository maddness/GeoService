package geoservice.response;

/**
 * Response ...
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
