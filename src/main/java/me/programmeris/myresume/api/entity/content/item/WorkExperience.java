package me.programmeris.myresume.api.entity.content.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import java.time.LocalDateTime;

import static me.programmeris.myresume.api.entity.content.ContentType.WORK_EXPERIENCE;

@Entity
@DiscriminatorValue(value = WORK_EXPERIENCE)
@Getter @Setter
public class WorkExperience extends ContentItem {

    private LocalDateTime startDt;
    private LocalDateTime endDt;
}
