package kr.myresume.api.entity.content.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import static kr.myresume.api.entity.content.ContentType.CERTIFICATION;

@Entity
@DiscriminatorValue(value = CERTIFICATION)
@Getter @Setter
public class Certification extends ContentItem {
}
