package me.programmeris.myresume.api.entity.content.item;

import lombok.Getter;
import lombok.Setter;
import me.programmeris.myresume.api.entity.base.Updatable;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="content_type",
        discriminatorType=DiscriminatorType.STRING)
@Getter @Setter
public abstract class ContentItem extends Updatable {

    @Id @GeneratedValue
    private Long id;

    private String contents;
}
