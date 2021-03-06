package kr.myresume.api.entity.content.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import static kr.myresume.api.entity.content.ContentType.CONTACT;

@Entity
@DiscriminatorValue(value = CONTACT)
@Getter @Setter
public class Contact extends ContentItem {
}
