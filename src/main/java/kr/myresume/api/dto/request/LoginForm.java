package kr.myresume.api.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import kr.myresume.api.dto.Password;

@Setter @Getter
@ToString(exclude = {"password"})
public class LoginForm {

    private String id;
    private Password password;
}
