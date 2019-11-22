package me.programmeris.myresume.api.entity.content.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import static me.programmeris.myresume.api.entity.content.ContentType.SELF_INTRODUCTION;

@Entity
@DiscriminatorValue(value = SELF_INTRODUCTION)
@Getter @Setter
public class SelfIntroduction extends ContentItem {
}
