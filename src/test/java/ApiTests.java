import com.fasterxml.jackson.annotation.JsonTypeInfo;
import controller.UserController;
import io.restassured.response.Response;
import models.GetUserResponse;
import models.User;
import models.UserResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static testData.TestData.DEFAULT_USER;
import static testData.TestData.INVALID_USER;

public class ApiTests {
    UserController userController = new UserController();

    @Test
    void createUser() {
        Response response = userController.createUser(DEFAULT_USER);
        UserResponse userResponse = response.as(UserResponse.class);

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals(200, userResponse.getCode());
        Assertions.assertEquals("unknown", userResponse.getType());
        Assertions.assertFalse(userResponse.getMessage().isEmpty());
    }

    @Test
    void createUser2() {
        Response response = userController.createUser(INVALID_USER);
        UserResponse userResponse = response.as(UserResponse.class);

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals(200, userResponse.getCode());
        Assertions.assertEquals("unknown", userResponse.getType());
        Assertions.assertFalse(userResponse.getMessage().isEmpty());
    }

    @Test
    void readUser() {
        Response response = userController.readUser("user1");
        GetUserResponse getUserResponse = response.as(GetUserResponse.class);

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals(11, getUserResponse.getId());
        //Assertions.assertEquals("user1", response.());
    }

    @Test
    void updateUser() {

    }

    @Test
    void deleteUser() {

    }
}
