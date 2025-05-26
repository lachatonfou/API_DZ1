import controller.UserController;
import io.restassured.response.Response;
import models.GetUserResponse;
import models.UserResponse;
import org.junit.jupiter.api.*;

import static testData.TestData.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class ApiTests {
    UserController userController = new UserController();

    @Test
    @Order(1)
    void createUser() throws InterruptedException {
        Response response = userController.createUser(DEFAULT_USER);
        UserResponse userResponse = response.as(UserResponse.class);

        Thread.sleep(10000);

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
    @Order(2)
    void readUser() throws InterruptedException {
        Response response = userController.readUser("Mike");
        GetUserResponse getUserResponse = response.as(GetUserResponse.class);

        Thread.sleep(10000);

        Assertions.assertEquals(200, response.statusCode());
        //Assertions.assertEquals(5, getUserResponse.getId());
        Assertions.assertEquals("Mike", getUserResponse.getUsername());
        Assertions.assertEquals("Alice", getUserResponse.getFirstName());
        Assertions.assertEquals("Volkova", getUserResponse.getLastName());
        Assertions.assertEquals("alice_volkova@mail.ru", getUserResponse.getEmail());
        Assertions.assertEquals("123", getUserResponse.getPassword());
        Assertions.assertEquals("88002000600", getUserResponse.getPhone());
        Assertions.assertEquals(3, getUserResponse.getUserStatus());
    }

    @Test
    @Order(3)
    void updateUser() throws InterruptedException {
        Response responseUpdate = userController.updateUser("Mike", UPDATED_USER);
        UserResponse UserResponse = responseUpdate.as(UserResponse.class);

        Assertions.assertEquals(200, responseUpdate.statusCode());
        Assertions.assertEquals(200, UserResponse.getCode());
        Assertions.assertEquals("unknown", UserResponse.getType());
        Assertions.assertFalse(UserResponse.getMessage().isEmpty());

        Thread.sleep(10000);

        Response responseRead = userController.readUser("Mike");
        GetUserResponse getUserResponseRead = responseRead.as(GetUserResponse.class);
        Assertions.assertEquals("Petrova", getUserResponseRead.getLastName());
    }

    @Test
    @Order(4)
    void deleteUser() throws InterruptedException {
        Response response = userController.deleteUser("Mike");

        Assertions.assertEquals(404, response.statusCode());

        Thread.sleep(10000);

        Response responseRead = userController.readUser("Mike");
        UserResponse UserResponse = responseRead.as(UserResponse.class);

        Assertions.assertEquals(404, responseRead.statusCode());
        Assertions.assertEquals("User not found", UserResponse.getMessage());
    }
}
