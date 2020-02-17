package me.programmeris.myresume.api.entity.content.item;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import me.programmeris.myresume.api.entity.content.Tag;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

import static me.programmeris.myresume.api.entity.content.ContentType.PROJECT_EXPERIENCE;

@Entity
@DiscriminatorValue(value = PROJECT_EXPERIENCE)
@Getter @Setter
public class ProjectExperience extends ContentItem {

    private LocalDateTime endDt;

    @ManyToMany
    @JoinTable(name = "item_tag",
            joinColumns = @JoinColumn(name="item_id"),
            inverseJoinColumns = @JoinColumn(name="tag_id")
    )
    private List<Tag> tags = Lists.newArrayList();
}
