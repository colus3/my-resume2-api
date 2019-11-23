package me.programmeris.myresume.api.dto.response;

import lombok.Getter;
import me.programmeris.myresume.api.entity.content.Content;
import me.programmeris.myresume.api.entity.content.item.ContentItem;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter
public class ContentDto implements ResponseData {

    private String type;
    private String name;
    private Long displayOrder;

    private List<ContentItemDto> contentItemDtos;

    public ContentDto(String type, String name, Long displayOrder, List<ContentItemDto> contentItemDtos) {
        this.type = type;
        this.name = name;
        this.displayOrder = displayOrder;
        this.contentItemDtos = contentItemDtos;
    }

    public static ContentDto of(Content content) {
        if (content == null) return null;

        return new ContentDto(content.getType(),
                content.getName(),
                content.getDisplayOrder(),
                content.getContentItems().stream().map(ContentItemDto::of).collect(toList()));
    }
}
