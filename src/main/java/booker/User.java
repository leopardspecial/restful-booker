package booker;


import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class User {
    private final String username;
    private final String password;
    private String token;
}
