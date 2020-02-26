package kr.myresume.api.entity.session;

import lombok.Getter;
import lombok.Setter;
import kr.myresume.api.entity.base.Updatable;
import kr.myresume.api.entity.user.User;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "token")
@Getter @Setter
public class AccessToken extends Updatable {

    @Id
    @GeneratedValue
    @Column(name = "token_id")
    private Long id;

    private String token;

    private LocalDateTime expiredDt;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
