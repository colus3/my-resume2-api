package me.programmeris.myresume.api.entity.content.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import static me.programmeris.myresume.api.entity.content.ContentType.INTEREST;

@Entity
@DiscriminatorValue(value = INTEREST)
@Getter @Setter
public class Interest extends ContentItem {
}
