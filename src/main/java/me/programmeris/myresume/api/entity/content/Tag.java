package me.programmeris.myresume.api.entity.content;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import me.programmeris.myresume.api.entity.base.Creatable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Setter @Getter
@ToString
public class Tag extends Creatable {

    @Id @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String name;

    public Tag() {}
    public Tag(String name) {
        this.name = name;
    }
}
