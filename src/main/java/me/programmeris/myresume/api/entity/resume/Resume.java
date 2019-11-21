package me.programmeris.myresume.api.entity.resume;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import me.programmeris.myresume.api.entity.base.Deletable;
import me.programmeris.myresume.api.entity.base.Updatable;
import me.programmeris.myresume.api.entity.user.User;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static javax.persistence.FetchType.*;

@Entity
@Getter @Setter
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

    @JsonIgnore
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "resume")
    private List<ResumeContent> resumeContents = new ArrayList<>();
}
