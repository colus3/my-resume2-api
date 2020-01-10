package me.programmeris.myresume.api.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import me.programmeris.myresume.api.entity.content.ContentType;
import me.programmeris.myresume.api.entity.content.item.*;

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

    private String tagName;
    private Double point;

    public ContentItemDto(String contentType, String title, String contents, LocalDateTime startDt, LocalDateTime endDt, String tagName, Double point) {
        this.contentType = contentType;
        this.title = title;
        this.contents = contents;
        this.startDt = startDt;
        this.endDt = endDt;
        this.tagName = tagName;
        this.point = point;
    }

    public static <T extends ContentItem> ContentItemDto of(T contentItem) {
        if (contentItem == null) return null;

        String contentType = contentItem.getContent().getType();
        switch (contentType) {
            case ContentType.PROFILE:
                return new ContentItemDto(contentItem.getContent().getType(),
                                          ((Profile) contentItem).getTitle(),
                                          contentItem.getContents(),
                                          null, null, null, null);

            case ContentType.EDUCATION:
                return new ContentItemDto(contentItem.getContent().getType(),
                        null,
                        contentItem.getContents(),
                        ((Education) contentItem).getStartDt(),
                        ((Education) contentItem).getEndDt(),
                        null, null);

            case ContentType.INTEREST:
                log.error("ContentItem.Class = {}", contentItem.getClass());
                log.error("ContentItem = {}", contentItem);
                log.error("ContentItem.Content = {}", contentItem.getContent());
                log.error("ContentItem.Tag = {}", ((Interest)contentItem).getTag().getName());
                return new ContentItemDto(contentItem.getContent().getType(),
                                          null,
                                          null,
                                          null, null,
                                          ((Interest) contentItem).getTag().getName(),
                                          null);

            case ContentType.SKILL:
                return new ContentItemDto(contentItem.getContent().getType(),
                                          null,
                                          contentItem.getContents(),
                                          null, null,
                                          ((Skill) contentItem).getTag().getName(),
                                          ((Skill) contentItem).getPoint());

            case ContentType.WORK_EXPERIENCE:
                return new ContentItemDto(contentItem.getContent().getType(),
                                          null,
                                          contentItem.getContents(),
                                          ((WorkExperience) contentItem).getStartDt(),
                                          ((WorkExperience) contentItem).getEndDt(),
                                          null, null);

            case ContentType.PROJECT_EXPERIENCE:
                return new ContentItemDto(contentItem.getContent().getType(),
                                          null,
                                          contentItem.getContents(),
                                          ((ProjectExperience) contentItem).getStartDt(),
                                          ((ProjectExperience) contentItem).getEndDt(),
                                          null, null);

            default:
                return new ContentItemDto(contentItem.getContent().getType(),
                        null,
                        contentItem.getContents(),
                        null,
                        null,
                        null,
                        null);
        }
    }
}
