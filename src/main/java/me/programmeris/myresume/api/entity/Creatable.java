package me.programmeris.myresume.api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

@Getter @Setter
@MappedSuperclass
public class Creatable {

    @Column(name = "create_id")
    private Long createUserId;
    private LocalDate createDate;
}
