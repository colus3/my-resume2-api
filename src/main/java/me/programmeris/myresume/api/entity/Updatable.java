package me.programmeris.myresume.api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

@Getter @Setter
@MappedSuperclass
public class Updatable extends Creatable {

    @Column(name = "update_id")
    private Long updateUserId;
    private LocalDate updateDate;
}
