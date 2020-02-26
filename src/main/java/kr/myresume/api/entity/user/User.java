package kr.myresume.api.entity.user;

import com.google.common.collect.Lists;
import kr.myresume.api.entity.resume.Resume;
import lombok.Getter;
import lombok.Setter;
import kr.myresume.api.entity.base.Address;
import kr.myresume.api.entity.base.Updatable;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter @Setter
@DynamicUpdate
public class User extends Updatable {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String phone;
    private String password;

    private String username;
    private Address address;
    private LocalDate birthDate;
    private String image;
    private String github;

    @OneToMany(mappedBy = "user")
    private List<Resume> resumes = Lists.newArrayList();
}
