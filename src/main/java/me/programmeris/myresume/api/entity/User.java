package me.programmeris.myresume.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

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

    private String userName;

    private String moto;
    private String email;
    private String phone;
    private String address;
    private LocalDate birthDate;
    private String image;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Resume> resumes = new ArrayList<>();
}
