package me.programmeris.myresume.api.entity.resume;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import me.programmeris.myresume.api.entity.base.Deletable;
import me.programmeris.myresume.api.entity.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
@ToString(exclude = {"user"})
public class Resume extends Deletable {

    @Id @GeneratedValue
    @Column(name = "resume_id")
    private Long id;

    private ResumeType resumeType;
    private String resumeName;
    private String defaultYn = "N";
    private String useYn = "N";
    private String directAccessId = UUID.randomUUID().toString();
    private String resumeShortUrl;
    @Column(name = "resume_ui_type")
    @Enumerated(EnumType.STRING)
    private ResumeUIType resumeUIType = ResumeUIType.bootstrap;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ResumeContent> resumeContents = new ArrayList<>();

    public void addResumeContents(ResumeContent resumeContent, ResumeContentItem... items) {
        resumeContent.setResume(this);
        if (items != null) {
            resumeContent.addResumeContentItems(items);
        }
        resumeContents.add(resumeContent);
    }
}
