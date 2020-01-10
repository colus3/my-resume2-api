package me.programmeris.myresume.api.entity.resume;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import me.programmeris.myresume.api.entity.base.Creatable;
import me.programmeris.myresume.api.entity.content.item.ContentItem;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
@ToString(exclude = {"resumeContent"})
public class ResumeContentItem extends Creatable {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "resume_content_id")
    private ResumeContent resumeContent;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "content_item_id")
    private ContentItem contentItem;
}
