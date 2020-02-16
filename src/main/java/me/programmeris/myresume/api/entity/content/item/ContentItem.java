package me.programmeris.myresume.api.entity.content.item;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import me.programmeris.myresume.api.entity.base.Updatable;
import me.programmeris.myresume.api.entity.content.Content;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="content_type",
        discriminatorType=DiscriminatorType.STRING)
@Getter @Setter
@ToString
public abstract class ContentItem extends Updatable {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "content_id")
    private Content content;

    @Column(columnDefinition = "TEXT")
    private String contents;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "parent_id")
    private ContentItem parent;

    @OneToMany(mappedBy = "parent")
    private List<ContentItem> childContentItems = Lists.newArrayList();
}
