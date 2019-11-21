package me.programmeris.myresume.api.entity.user;

import lombok.Getter;
import lombok.Setter;
import me.programmeris.myresume.api.entity.base.Updatable;
import me.programmeris.myresume.api.entity.resume.Resume;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter @Setter
public class User extends Updatable {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String username;

    private String moto;
    private String email;
    private String phone;
    private String address;
    private LocalDate birthDate;
    private String image;

    @OneToMany(mappedBy = "user")
    private List<Resume> resumes = new ArrayList<>();
}
