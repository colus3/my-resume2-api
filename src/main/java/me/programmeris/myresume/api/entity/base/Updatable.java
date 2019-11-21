package me.programmeris.myresume.api.entity.base;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter @Setter
@MappedSuperclass
public class Updatable extends Creatable {

    @Column(name = "update_id")
    private Long updateUserId;
    private LocalDateTime updateDt = LocalDateTime.now();
}
