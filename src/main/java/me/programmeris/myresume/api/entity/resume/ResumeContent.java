package me.programmeris.myresume.api.entity.resume;

import lombok.Getter;
import lombok.Setter;
import me.programmeris.myresume.api.entity.base.Creatable;
import me.programmeris.myresume.api.entity.content.Content;
import me.programmeris.myresume.api.entity.content.ContentType;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter @Setter
public class ResumeContent extends Creatable {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "resume_id")
    private Resume resume;

    @Enumerated(value = EnumType.STRING)
    private ContentType contentType;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "content_id")
    private Content content;
    private String displayName;
    @Column(name = "ord")
    private Long order;
    @Enumerated(value = EnumType.STRING)
    private Position position;
}
