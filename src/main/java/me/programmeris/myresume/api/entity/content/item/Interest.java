package me.programmeris.myresume.api.entity.content.item;

import lombok.Getter;
import lombok.Setter;
import me.programmeris.myresume.api.entity.content.Tag;

import javax.persistence.*;

import static me.programmeris.myresume.api.entity.content.ContentType.INTEREST;

@Entity
@DiscriminatorValue(value = INTEREST)
@Getter @Setter
public class Interest extends ContentItem {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")
    private Tag tag;
}
