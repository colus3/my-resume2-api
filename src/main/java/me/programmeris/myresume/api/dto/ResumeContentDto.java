package me.programmeris.myresume.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import me.programmeris.myresume.api.entity.resume.Position;
import me.programmeris.myresume.api.entity.resume.ResumeContent;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter
public class ResumeContentDto implements ResponseData {

    private String displayName;
    private Long displayOrder;
    private Position position;
    @JsonProperty(value = "content")
    private List<ResumeContentItemDto> resumeContentItemDtos;

    private ResumeContentDto(String displayName, Long displayOrder, Position position, List<ResumeContentItemDto> resumeContentItemDtos) {
        this.displayName = displayName;
        this.displayOrder = displayOrder;
        this.position = position;
        this.resumeContentItemDtos = resumeContentItemDtos;
    }

    public static ResumeContentDto of(ResumeContent resumeContent) {

        return new ResumeContentDto(resumeContent.getDisplayName(),
                resumeContent.getDisplayOrder(),
                resumeContent.getPosition(),
                resumeContent.getContent().getContentItems()
                        .stream()
                        .map(ResumeContentItemDto::of)
                        .collect(toList()));
    }
}
