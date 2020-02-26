package kr.myresume.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import kr.myresume.api.entity.content.item.ContentItem;
import kr.myresume.api.entity.resume.Position;
import kr.myresume.api.entity.resume.ResumeContent;
import lombok.Getter;
import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter
public class ResumeContentDto implements ResponseData {

    private String type;
    private String name;
    private Long displayOrder;
    private Position position;
    @JsonProperty(value = "items")
    private List<ResumeContentItemDto> resumeContentItemDtos;

    private ResumeContentDto(String type, String name, Long displayOrder, Position position, List<ResumeContentItemDto> resumeContentItemDtos) {
        this.type = type;
        this.name = name;
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
                        .peek(e -> {
                            ContentItem contentItem = e.getContentItem();
                            if (contentItem instanceof HibernateProxy) {
                                e.setContentItem((ContentItem) Hibernate.unproxy(contentItem));
                            }
                        })
                        .map(ResumeContentItemDto::of)
                        .collect(toList())
        );
    }
}
