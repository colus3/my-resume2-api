package me.programmeris.myresume.api.entity.session;

import lombok.Getter;
import lombok.Setter;
import me.programmeris.myresume.api.entity.base.Updatable;
import me.programmeris.myresume.api.entity.user.User;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class AccessToken extends Updatable {

    @Id
    @GeneratedValue
    @Column(name = "token_id")
    private Long id;

    private String key;

    private LocalDateTime expiredDT;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
