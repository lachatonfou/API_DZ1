package controller;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.User;
import testData.TestData;

import static constants.CommonConstants.BASE_URI;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class UserController {
    RequestSpecification requestSpecification;
    private static final String USER_ENDPOINT = "user";

    public UserController() {
        this.requestSpecification = given()
                .accept(JSON)
                .contentType(JSON)
                .baseUri(BASE_URI);
    }

    public Response createUser (User user) {
        return given(this.requestSpecification)
                    .body(user)
                .when()
                    .post(USER_ENDPOINT)
                    .andReturn();
    }

    public Response readUser (String username) {
        return given(this.requestSpecification)
                .when()
                    .get(USER_ENDPOINT + "/" + username)
                    .andReturn();
    }

    public Response updateUser (String username, User user) {
        return given(this.requestSpecification)
                    .body(user)
                .when()
                    .put(USER_ENDPOINT + "/" + username)
                    .andReturn();
    }

    public Response deleteUser (String username) {
        return given(this.requestSpecification)
                .when()
                    .delete(USER_ENDPOINT + "/" + username)
                    .andReturn();
    }
}
