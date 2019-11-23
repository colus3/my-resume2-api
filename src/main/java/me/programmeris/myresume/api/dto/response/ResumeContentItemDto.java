package me.programmeris.myresume.api.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import me.programmeris.myresume.api.entity.content.item.ContentItem;

import java.time.LocalDateTime;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResumeContentItemDto implements ResponseData {

    private String contentType;
    private String title;
    private String contents;
    private LocalDateTime startDt;
    private LocalDateTime endDt;

    private Double skillPoint;

    public ResumeContentItemDto(String contentType, String title, String contents, LocalDateTime startDt, LocalDateTime endDt, Double skillPoint) {
        this.contentType = contentType;
        this.title = title;
        this.contents = contents;
        this.startDt = startDt;
        this.endDt = endDt;
        this.skillPoint = skillPoint;
    }

    public static <T extends ContentItem> ResumeContentItemDto of(T contentItem) {

        return new ResumeContentItemDto(contentItem.getContent().getType(),
                null,
                contentItem.getContents(),
                null,
                null,
                null);
    }
}
