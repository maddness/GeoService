package geoservice.response;

/**
 * Created by Alexey Ostrikov on 26/03/2016.
 */
public class ErrorResponse implements Response {
    private final String error;

    public ErrorResponse(String error) {
        this.error = error;
    }
}
