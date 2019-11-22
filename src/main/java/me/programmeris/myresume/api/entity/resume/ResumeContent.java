package me.programmeris.myresume.api.entity.resume;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import me.programmeris.myresume.api.entity.base.Creatable;
import me.programmeris.myresume.api.entity.content.Content;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class ResumeContent extends Creatable {

    @Id @GeneratedValue
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "resume_id")
    private Resume resume;

    @JsonIgnore
    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "content_id")
    private Content content;
    private String displayName;
    private Long displayOrder;
    @Enumerated(value = EnumType.STRING)
    private Position position;
}
