package me.programmeris.myresume.api.entity.base;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter @Setter
@MappedSuperclass
public class Deletable extends Updatable {

    @Column(name = "delete_id")
    private Long deleteUserId;
    private LocalDateTime deleteDt;
}
