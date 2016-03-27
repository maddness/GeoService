package geoservice.response;

/**
 * Created by Alexey Ostrikov on 26/03/2016.
 */
public class UserCountResponse implements Response {

    private final int userCount;

    public UserCountResponse(int userCount) {
        this.userCount = userCount;
    }

    public int getUserCount() {
        return userCount;
    }
}
