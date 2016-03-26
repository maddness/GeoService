package geoservice.model;

import org.junit.Test;

/**
 * Created by Alexey Ostrikov on 26/03/2016.
 */
public class UserTest {

    @Test(expected = NumberFormatException.class)
    public void cantCreateUserWithInvalidCoordinates() {
        User.newUser(1112, 34, -564);
    }
}