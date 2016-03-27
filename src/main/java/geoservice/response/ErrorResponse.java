package geoservice.response;

/**
 * Response with error description, will be sent in case of unsuccessful operation.
 */
public class ErrorResponse implements Response {
    private final String error;

    public ErrorResponse(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
