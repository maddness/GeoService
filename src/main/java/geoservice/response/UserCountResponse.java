package geoservice.response;

/**
 * Created by Alexey Ostrikov on 26/03/2016.
 */
public class UserCountResponse implements Response {

    private final int userCount;
    private final Status status;

    public UserCountResponse(int userCount, Status status) {
        this.userCount = userCount;
        this.status = status;
    }

    public int getUserCount() {
        return userCount;
    }

    public Status getStatus() {
        return status;
    }
}
