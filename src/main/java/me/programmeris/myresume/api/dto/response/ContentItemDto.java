package me.programmeris.myresume.api.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import me.programmeris.myresume.api.entity.content.ContentType;
import me.programmeris.myresume.api.entity.content.item.*;

import java.time.LocalDateTime;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
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

        String title = null;
        String contents = contentItem.getContents();
        LocalDateTime startDt = null;
        LocalDateTime endDt = null;
        String tagName = null;
        Double point = null;
        String contentType = contentItem.getContent().getType();
        switch (contentType) {
            case ContentType.PROFILE:
                title = ((Profile) contentItem).getTitle();
                break;

            case ContentType.EDUCATION:
                startDt = ((Education) contentItem).getStartDt();
                endDt = ((Education) contentItem).getEndDt();
                break;

            case ContentType.INTEREST:
                tagName = ((Interest) contentItem).getTag().getName();
                break;

            case ContentType.SKILL:
                tagName = ((Skill) contentItem).getTag().getName();
                point = ((Skill) contentItem).getPoint();
                break;

            case ContentType.WORK_EXPERIENCE:
                startDt = ((WorkExperience) contentItem).getStartDt();
                endDt = ((WorkExperience) contentItem).getEndDt();
                break;

            case ContentType.PROJECT_EXPERIENCE:
                startDt = ((ProjectExperience) contentItem).getStartDt();
                endDt = ((ProjectExperience) contentItem).getEndDt();
                break;

            default: break;
        }
        return new ContentItemDto(contentType,
                title,
                contents,
                startDt,
                endDt,
                tagName,
                point);

    }
}
