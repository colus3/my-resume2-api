package me.programmeris.myresume.api.entity.content;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import me.programmeris.myresume.api.entity.base.Updatable;
import me.programmeris.myresume.api.entity.content.item.ContentItem;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@ToString
public class Content extends Updatable {

    @Id @GeneratedValue
    private Long id;

    private String type;
    private String name;
    private Long displayOrder;

    @OneToMany(mappedBy = "content", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ContentItem> contentItems = new ArrayList<>();

    public void addContentItem(ContentItem... items) {
        if (items == null) return;
        for (ContentItem item : items) {
            item.setContent(this);
            contentItems.add(item);
        }
    }
}
