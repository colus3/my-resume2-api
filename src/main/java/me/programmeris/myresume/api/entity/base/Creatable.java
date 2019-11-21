package me.programmeris.myresume.api.entity.base;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter @Setter
@MappedSuperclass
public class Creatable {

    @Column(name = "create_id")
    private Long createUserId;
    private LocalDateTime createDt = LocalDateTime.now();
}
