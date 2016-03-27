package geoservice.model;

import org.junit.Test;

public class UserTest {

    @Test(expected = NumberFormatException.class)
    public void cantCreateUserWithInvalidCoordinates() {
        User.newUser(1112, 34, -564);
    }
}