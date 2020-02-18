package me.programmeris.myresume.api.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import me.programmeris.myresume.api.entity.content.ContentType;
import me.programmeris.myresume.api.entity.content.item.ContentItem;
import me.programmeris.myresume.api.entity.resume.ResumeContentItem;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
@Slf4j
public class ResumeContentItemDto implements ResponseData {

    @JsonIgnore
    private String contentType;
    private String title;
    private String contents;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime startDt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime endDt;

    private List<String> tagNames;
    private Double point;

    @JsonProperty(value = "childItems")
    private List<ContentItemDto> childContentItemDtos;

    private Long displayOrder;

    public ResumeContentItemDto(String contentType, String title, String contents, LocalDateTime startDt, LocalDateTime endDt, List<String> tagNames, Double point, List<ContentItemDto> childContentItemDtos, Long displayOrder) {
        this.contentType = contentType;
        this.title = title;
        this.contents = contents;
        this.startDt = startDt;
        this.endDt = endDt;
        this.tagNames = tagNames;
        this.point = point;
        this.childContentItemDtos = childContentItemDtos;
        this.displayOrder = displayOrder;
    }

    public static <T extends ResumeContentItem> ResumeContentItemDto of(T resumeContentItem) {

        ContentItemDto contentItemDto = ContentItemDto.of(resumeContentItem.getContentItem());

        if (contentItemDto.getContentType().equals(ContentType.SKILL)) {
            log.info("skill {}", contentItemDto);
        }

        return new ResumeContentItemDto(contentItemDto.getContentType(),
                contentItemDto.getTitle(),
                contentItemDto.getContents(),
                contentItemDto.getStartDt(),
                contentItemDto.getEndDt(),
                contentItemDto.getTagNames(),
                contentItemDto.getPoint(),
                contentItemDto.getChildContentItemDtos(),
                resumeContentItem.getDisplayOrder());
    }
}
