package me.programmeris.myresume.api.entity.user;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import me.programmeris.myresume.api.entity.base.Address;
import me.programmeris.myresume.api.entity.base.Updatable;
import me.programmeris.myresume.api.entity.resume.Resume;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
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
