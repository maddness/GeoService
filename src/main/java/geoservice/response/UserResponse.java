package geoservice.response;

/**
 * Response ...
 */
public class UserResponse implements Response {

    private final String message;
    private final Status status;

    public UserResponse(String message, Status status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public Status getStatus() {
        return status;
    }
}
