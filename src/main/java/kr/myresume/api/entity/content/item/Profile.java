package kr.myresume.api.entity.content.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import static kr.myresume.api.entity.content.ContentType.PROFILE;

@Entity
@DiscriminatorValue(value = PROFILE)
@Getter @Setter
public class Profile extends ContentItem {

    private String title;
}
