package me.programmeris.myresume.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import me.programmeris.myresume.api.entity.content.Content;
import me.programmeris.myresume.api.entity.content.item.ContentItem;
import me.programmeris.myresume.api.entity.resume.Position;
import me.programmeris.myresume.api.entity.resume.ResumeContent;
import me.programmeris.myresume.api.entity.resume.ResumeContentItem;
import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter
@Slf4j
public class ResumeContentDto implements ResponseData {

    private String contentType;
    private String displayName;
    private Long displayOrder;
    private Position position;
    @JsonProperty(value = "content")
    private List<ResumeContentItemDto> resumeContentItemDtos;
//    private List<ContentItemDto> contentItemDtos;

    private ResumeContentDto(String contentType, String displayName, Long displayOrder, Position position, List<ResumeContentItemDto> resumeContentItemDtos) {
        this.contentType = contentType;
        this.displayName = displayName;
        this.displayOrder = displayOrder;
        this.position = position;
        this.resumeContentItemDtos = resumeContentItemDtos;
//        this.contentItemDtos = contentItemDto;
    }

    public static ResumeContentDto of(ResumeContent resumeContent) {

//        resumeContent.getResumeContentItems().forEach(e -> log.error("ResumeContentItem.Class {}", Hibernate.unproxy(e).getClass()));
//        resumeContent.getContent().getContentItems().forEach(e -> log.error("ContentItem.Class {}", Hibernate.unproxy(e).getClass()));

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
//                resumeContent.getContent().getContentItems().stream().map(ContentItemDto::of).collect(toList())
        );
    }
}
