package me.programmeris.myresume.api.entity.content.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import java.time.LocalDateTime;

import static me.programmeris.myresume.api.entity.content.ContentType.PROJECT_EXPERIENCE;

@Entity
@DiscriminatorValue(value = PROJECT_EXPERIENCE)
@Getter @Setter
public class ProjectExperience extends ContentItem {

    private LocalDateTime startDt;
    private LocalDateTime endDt;
}
