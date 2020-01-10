package me.programmeris.myresume.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import me.programmeris.myresume.api.entity.content.item.ContentItem;
import me.programmeris.myresume.api.entity.resume.Position;
import me.programmeris.myresume.api.entity.resume.ResumeContent;
import me.programmeris.myresume.api.entity.resume.ResumeContentItem;
import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter
public class ResumeContentDto implements ResponseData {

    private String contentType;
    private String displayName;
    private Long displayOrder;
    private Position position;
    @JsonProperty(value = "content")
    private List<ResumeContentItemDto> resumeContentItemDtos;

    private ResumeContentDto(String contentType, String displayName, Long displayOrder, Position position, List<ResumeContentItemDto> resumeContentItemDtos) {
        this.contentType = contentType;
        this.displayName = displayName;
        this.displayOrder = displayOrder;
        this.position = position;
        this.resumeContentItemDtos = resumeContentItemDtos;
    }

    public static ResumeContentDto of(ResumeContent resumeContent) {

        return new ResumeContentDto(
                resumeContent.getContent().getType(),
                resumeContent.getDisplayName(),
                resumeContent.getDisplayOrder(),
                resumeContent.getPosition(),
                resumeContent.getResumeContentItems()
                        .stream()
                        .map(ResumeContentItem::getContentItem)
                        .map(e -> e instanceof HibernateProxy ? (ContentItem) Hibernate.unproxy(e) : e)
                        .map(ResumeContentItemDto::of)
                        .collect(toList())
        );
    }
}
