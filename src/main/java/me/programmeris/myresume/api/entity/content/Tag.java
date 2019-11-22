package me.programmeris.myresume.api.entity.content;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import me.programmeris.myresume.api.entity.base.Creatable;

import javax.persistence.*;

@Entity
@Setter @Getter
public class Tag extends Creatable {

    @Id @GeneratedValue
    private Long id;

    private String name;
}
