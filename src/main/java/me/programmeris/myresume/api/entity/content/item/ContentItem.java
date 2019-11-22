package me.programmeris.myresume.api.entity.content.item;

import lombok.Getter;
import lombok.Setter;
import me.programmeris.myresume.api.entity.base.Updatable;
import me.programmeris.myresume.api.entity.content.Content;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="content_type",
        discriminatorType=DiscriminatorType.STRING)
@Getter @Setter
public abstract class ContentItem extends Updatable {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "content_id")
    private Content content;

    private String contents;
}
