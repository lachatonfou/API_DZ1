package testData;

import models.User;

public class TestData {
    public static final User DEFAULT_USER = User.builder()
            .id(555)
            .username("Mike")
            .firstName("Alice")
            .lastName("Volkova")
            .email("alice_volkova@mail.ru")
            .password("123")
            .phone("88002000600")
            .userStatus(3)
            .build();

    public static final User UPDATED_USER = User.builder()
            .id(555)
            .username("Mike")
            .firstName("Alice")
            .lastName("Petrova")
            .email("alice_volkova@mail.ru")
            .password("123")
            .phone("88002000600")
            .userStatus(3)
            .build();

    public static final User INVALID_USER = User.builder().build();
}
