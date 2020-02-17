package me.programmeris.myresume.api.entity.content.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDateTime;

import static me.programmeris.myresume.api.entity.content.ContentType.EDUCATION;

@Entity
@DiscriminatorValue(value = EDUCATION)
@Getter @Setter
public class Education extends ContentItem {

    private LocalDateTime endDt;
}
