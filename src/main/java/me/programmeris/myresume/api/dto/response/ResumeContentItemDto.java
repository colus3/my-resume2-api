package me.programmeris.myresume.api.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import me.programmeris.myresume.api.entity.content.item.ContentItem;

import java.time.LocalDateTime;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResumeContentItemDto implements ResponseData {

    @JsonIgnore
    private String contentType;
    private String title;
    private String contents;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime startDt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime endDt;

    private String tagName;
    private Double point;

    public ResumeContentItemDto(String contentType, String title, String contents, LocalDateTime startDt, LocalDateTime endDt, String tagName, Double point) {
        this.contentType = contentType;
        this.title = title;
        this.contents = contents;
        this.startDt = startDt;
        this.endDt = endDt;
        this.tagName = tagName;
        this.point = point;
    }

    public static <T extends ContentItem> ResumeContentItemDto of(T contentItem) {

        ContentItemDto contentItemDto = ContentItemDto.of(contentItem);

        return new ResumeContentItemDto(contentItemDto.getContentType(),
                contentItemDto.getTitle(),
                contentItemDto.getContents(),
                contentItemDto.getStartDt(),
                contentItemDto.getEndDt(),
                contentItemDto.getTagName(),
                contentItemDto.getPoint());
    }
}
