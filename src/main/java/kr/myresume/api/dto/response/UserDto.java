package kr.myresume.api.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import kr.myresume.api.entity.base.Address;
import kr.myresume.api.entity.user.User;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class UserDto implements ResponseData {

    private String username;
    private String phone;
    private String email;
    private String image;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    private String address;
    private String github;
    private LocalDateTime createDt;

    public UserDto(String username,
                   String phone,
                   String email,
                   String image,
                   LocalDate birthDate,
                   String zipCode,
                   String address1,
                   String address2,
                   String github,
                   LocalDateTime createDt) {
        this.username = username;
        this.phone = phone;
        this.email = email;
        this.image = image;
        this.address = new Address(zipCode,
                                   address1,
                                   address2).toFullAddress();
        this.birthDate = birthDate;
        this.github = github;
        this.createDt = createDt;
    }

    public static UserDto of(User user) {
        if (user == null) return null;

        return new UserDto(user.getUsername(),
                user.getPhone(),
                user.getEmail(),
                user.getImage(),
                user.getBirthDate(),
                user.getAddress() != null ? user.getAddress().getZipCode() : "",
                user.getAddress() != null ? user.getAddress().getAddress1() : "",
                user.getAddress() != null ? user.getAddress().getAddress2() : "",
                user.getGithub(),
                user.getCreateDt());
    }

    public User to(User user) {
        if (user == null) user = new User();

        user.setEmail(this.getEmail());
        user.setUsername(this.getUsername());
        user.setPhone(this.getPhone());
        user.setImage(this.getImage());
        user.setBirthDate(this.getBirthDate());

        return user;
    }
}
