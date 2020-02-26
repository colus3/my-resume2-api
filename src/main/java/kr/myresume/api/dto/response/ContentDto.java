package kr.myresume.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import kr.myresume.api.entity.content.Content;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter
public class ContentDto implements ResponseData {

    private String type;
    private String name;
    private Long displayOrder;
    private LocalDateTime createDt;
    @JsonProperty(value = "items")
    private List<ContentItemDto> contentItemDtos;

    public ContentDto(String type, String name, Long displayOrder, LocalDateTime createDt, List<ContentItemDto> contentItemDtos) {
        this.type = type;
        this.name = name;
        this.displayOrder = displayOrder;
        this.createDt = createDt;
        this.contentItemDtos = contentItemDtos;
    }

    public static ContentDto of(Content content) {
        if (content == null) return null;

        return new ContentDto(content.getType(),
                content.getName(),
                content.getDisplayOrder(),
                content.getCreateDt(),
                content.getContentItems().stream().map(ContentItemDto::of).collect(toList()));
    }

    public Content toEntity() {
        Content newContent = new Content();
        newContent.setType(type);
        newContent.setName(name);
        newContent.setDisplayOrder(displayOrder);
        // TODO: ContentItem 추가하는 로직 넣아야 함.

        return newContent;
    }
}
