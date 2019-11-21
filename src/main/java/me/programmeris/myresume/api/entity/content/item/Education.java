package me.programmeris.myresume.api.entity.content.item;

import lombok.Getter;
import lombok.Setter;
import me.programmeris.myresume.api.entity.content.ContentType;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@DiscriminatorValue(value = ContentType.EDUCATION)
@Getter @Setter
public class Education extends ContentItem {

    private LocalDate startDate;
    private LocalDate endDate;
}
