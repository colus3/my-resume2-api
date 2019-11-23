package me.programmeris.myresume.api.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import me.programmeris.myresume.api.entity.content.ContentType;
import me.programmeris.myresume.api.entity.content.item.ContentItem;
import me.programmeris.myresume.api.entity.content.item.Education;

import java.time.LocalDateTime;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Slf4j
public class ContentItemDto implements ResponseData {

    private String contentType;
    private String title;
    private String contents;
    private LocalDateTime startDt;
    private LocalDateTime endDt;

    private Double skillPoint;

    public ContentItemDto(String contentType, String title, String contents, LocalDateTime startDt, LocalDateTime endDt, Double skillPoint) {
        this.contentType = contentType;
        this.title = title;
        this.contents = contents;
        this.startDt = startDt;
        this.endDt = endDt;
        this.skillPoint = skillPoint;
    }

    public static <T extends ContentItem> ContentItemDto of(T contentItem) {
        if (contentItem == null) return null;

        switch (contentItem.getContent().getType()) {
            case ContentType.EDUCATION:
                return new ContentItemDto(contentItem.getContent().getType(),
                        null,
                        contentItem.getContents(),
                        ((Education) contentItem).getStartDt(),
                        ((Education) contentItem).getEndDt(),
                        null);

            default:
                return new ContentItemDto(contentItem.getContent().getType(),
                        null,
                        contentItem.getContents(),
                        null,
                        null,
                        null);
        }
    }
}
