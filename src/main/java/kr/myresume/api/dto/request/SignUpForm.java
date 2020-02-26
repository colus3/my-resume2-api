package kr.myresume.api.dto.request;

import kr.myresume.api.entity.base.Address;
import kr.myresume.api.entity.user.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import kr.myresume.api.dto.Password;

import java.time.LocalDate;

@Getter @Setter
@ToString(exclude = {"password"})
public class SignUpForm {

    private String email;
    private String phone;
    private Password password;

    private String username;
    private Address address;
    private LocalDate birthDate;

    public User toUser() {
        User user = new User();
        user.setEmail(email);
        user.setPhone(phone.replaceAll("-", ""));
        user.setPassword(password.get());
        user.setUsername(username);
        user.setAddress(address);
        user.setBirthDate(birthDate);
        return user;
    }
}
