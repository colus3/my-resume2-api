package me.programmeris.myresume.api.dto.response;

import lombok.Getter;
import me.programmeris.myresume.api.entity.base.Address;
import me.programmeris.myresume.api.entity.user.User;

import java.time.LocalDateTime;

@Getter
public class UserDto implements ResponseData {

    private String username;
    private String phone;
    private String email;
    private Address address;
    private LocalDateTime createDt;

    public UserDto(String username,
                   String phone,
                   String email,
                   String zipCode,
                   String address1,
                   String address2,
                   LocalDateTime createDt) {
        this.username = username;
        this.phone = phone;
        this.email = email;
        this.address = new Address(zipCode,
                                   address1,
                                   address2);
        this.createDt = createDt;
    }

    public static UserDto of(User user) {
        if (user == null) return null;

        return new UserDto(user.getUsername(),
                           user.getPhone(),
                           user.getEmail(),
                           user.getAddress() != null ? user.getAddress().getZipCode() : "",
                           user.getAddress() != null ? user.getAddress().getAddress1() : "",
                           user.getAddress() != null ? user.getAddress().getAddress2() : "",
                           user.getCreateDt());
    }

    public User to(User user) {
        if (user == null) user = new User();

        user.setEmail(this.getEmail());
        user.setUsername(this.getUsername());
        user.setPhone(this.getPhone());
        user.setAddress(this.getAddress());
        user.setCreateDt(this.getCreateDt());

        return user;
    }
}
