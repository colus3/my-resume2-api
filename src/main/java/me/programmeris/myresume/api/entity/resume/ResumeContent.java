package me.programmeris.myresume.api.entity.resume;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import me.programmeris.myresume.api.entity.base.Creatable;
import me.programmeris.myresume.api.entity.content.Content;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
@ToString(exclude = {"resume", "content", "resumeContentItems"})
public class ResumeContent extends Creatable {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "resume_id")
    private Resume resume;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "content_id")
    private Content content;
    private String displayName;
    private Long displayOrder;
    @Enumerated(value = EnumType.STRING)
    private Position position;

    @OneToMany(mappedBy = "resumeContent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ResumeContentItem> resumeContentItems = Lists.newArrayList();

    public void addResumeContentItems(ResumeContentItem... items) {
        for (ResumeContentItem item : items) {
            item.setResumeContent(this);
            resumeContentItems.add(item);
        }
    }
}
