package me.programmeris.myresume.api.entity.content;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import me.programmeris.myresume.api.entity.base.Creatable;

import javax.persistence.*;

@Entity
@Setter @Getter
public class Skill extends Creatable {

    @Id @GeneratedValue
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id")
    private Content content;

    private String name;

}
