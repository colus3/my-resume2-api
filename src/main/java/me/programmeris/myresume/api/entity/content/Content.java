package me.programmeris.myresume.api.entity.content;

import lombok.Getter;
import lombok.Setter;
import me.programmeris.myresume.api.entity.base.Updatable;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Content extends Updatable {

    @Id @GeneratedValue
    private Long id;

    private LocalDate startDate;
    private LocalDate endDate;

    private ContentType contentType;
    private String name;

    @OneToMany(mappedBy = "content")
    private List<Skill> skills = new ArrayList<>();

    private Integer displayOrder;
}
