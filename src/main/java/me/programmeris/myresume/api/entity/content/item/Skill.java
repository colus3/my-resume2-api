package me.programmeris.myresume.api.entity.content.item;

import lombok.Getter;
import lombok.Setter;
import me.programmeris.myresume.api.entity.content.Tag;

import javax.persistence.*;

import static me.programmeris.myresume.api.entity.content.ContentType.SKILL;

@Entity
@DiscriminatorValue(value = SKILL)
@Getter @Setter
public class Skill extends ContentItem {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")
    private Tag tag;
    private Double point;
}
