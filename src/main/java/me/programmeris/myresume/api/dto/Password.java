package me.programmeris.myresume.api.dto;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Password {

    private String encryptedPassword;

    public Password(String password) {
        set(password);
    }

    public void set(String password) {
        this.encryptedPassword = DigestUtils.sha512Hex(password).toUpperCase();
    }

    public String get() {
        return encryptedPassword;
    }
}
