package me.programmeris.myresume.api.entity.content.item;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import me.programmeris.myresume.api.entity.content.Tag;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

import static me.programmeris.myresume.api.entity.content.ContentType.WORK_EXPERIENCE;

@Entity
@DiscriminatorValue(value = WORK_EXPERIENCE)
@Getter @Setter
public class WorkExperience extends ContentItem {

    private String title;
    private LocalDateTime startDt;
    private LocalDateTime endDt;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "tag_id")
//    private Tag tag;

    @ManyToMany
    @JoinTable(name = "item_tag",
            joinColumns = @JoinColumn(name="item_id"),
            inverseJoinColumns = @JoinColumn(name="tag_id")
    )
    private List<Tag> tags = Lists.newArrayList();
}
