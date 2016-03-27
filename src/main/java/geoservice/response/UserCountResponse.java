package geoservice.response;

/**
 * Response with information about number of users inside the cell.
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
