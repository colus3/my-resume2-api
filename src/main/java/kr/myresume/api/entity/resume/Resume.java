package kr.myresume.api.entity.resume;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import kr.myresume.api.entity.base.Deletable;
import kr.myresume.api.entity.user.User;

import javax.persistence.*;
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

    private ResumeType type;
    private String name;
    private String shortIntro = "";
    private String defaultYn = "N";
    private String useYn = "N";
    private String directAccessId = UUID.randomUUID().toString();
    private String resumeShortUrl;
    @Column(name = "ui_type")
    @Enumerated(EnumType.STRING)
    private ResumeUIType resumeUIType = ResumeUIType.bootstrap;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ResumeContent> resumeContents = Lists.newArrayList();

    public void addResumeContents(ResumeContent resumeContent, ResumeContentItem... items) {
        resumeContent.setResume(this);
        if (items != null) {
            resumeContent.addResumeContentItems(items);
        }
        resumeContents.add(resumeContent);
    }
}
