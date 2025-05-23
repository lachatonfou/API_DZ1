package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
public class CreateUserResponse {
    private int code;
    private String type;
    private String message;
}
