package booker;


import lombok.Data;

@Data
public class User {
    private final String username;
    private final String password;
    private String token;
}
