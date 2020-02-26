package kr.myresume.api.entity.base;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter @Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Updatable extends Creatable {

    @LastModifiedBy
    @Column(name = "update_id")
    private Long updateUserId;

    @LastModifiedDate
    private LocalDateTime updateDt;
}
