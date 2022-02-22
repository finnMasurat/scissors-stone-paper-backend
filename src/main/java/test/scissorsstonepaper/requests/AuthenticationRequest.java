package test.scissorsstonepaper.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest implements Serializable {
    private String email;
    private String password;

    @Override
    public String toString() {
        return "AuthenticationRequest{" +
                "email=" + email +
                ", password='" + password + '\'' +
                '}';
    }
}
