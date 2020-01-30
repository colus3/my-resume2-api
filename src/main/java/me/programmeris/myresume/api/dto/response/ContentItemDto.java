package me.programmeris.myresume.api.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.collect.Lists;
import lombok.Getter;
import me.programmeris.myresume.api.entity.content.ContentType;
import me.programmeris.myresume.api.entity.content.Tag;
import me.programmeris.myresume.api.entity.content.item.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContentItemDto implements ResponseData {

    private String contentType;
    private String title;
    private String contents;
    private LocalDateTime startDt;
    private LocalDateTime endDt;

    private List<String> tagNames;
    private Double point;

    public ContentItemDto(String contentType, String title, String contents, LocalDateTime startDt, LocalDateTime endDt, List<String> tagNames, Double point) {
        this.contentType = contentType;
        this.title = title;
        this.contents = contents;
        this.startDt = startDt;
        this.endDt = endDt;
        this.tagNames = tagNames;
        this.point = point;
    }

    public static <T extends ContentItem> ContentItemDto of(T contentItem) {
        if (contentItem == null) return null;

        String title = null;
        String contents = contentItem.getContents();
        LocalDateTime startDt = null;
        LocalDateTime endDt = null;
        List<String> tagNames = Lists.newArrayList();
        Double point = null;
        String contentType = contentItem.getContent().getType();
        switch (contentType) {
            case ContentType.PROFILE:
                Profile profile = ((Profile) contentItem);
                title = profile.getTitle();
                break;

            case ContentType.EDUCATION:
                Education education = ((Education) contentItem);
                startDt = education.getStartDt();
                endDt = education.getEndDt();
                break;

            case ContentType.CERTIFICATION:
                Certification certification = ((Certification) contentItem);
                startDt = certification.getStartDt();
                break;

            case ContentType.INTEREST:
                Interest interest = ((Interest) contentItem);
                tagNames = interest.getTags().stream().map(Tag::getName).collect(toList());
                break;

            case ContentType.SKILL:
                Skill skill = ((Skill) contentItem);
                tagNames = skill.getTags().stream().map(Tag::getName).collect(toList());
                point = skill.getPoint();
                break;

            case ContentType.WORK_EXPERIENCE:
                WorkExperience workExperience = ((WorkExperience) contentItem);
                title = workExperience.getTitle();
                startDt = workExperience.getStartDt();
                endDt = workExperience.getEndDt();
                tagNames = workExperience.getTags().stream().map(Tag::getName).collect(toList());
                break;

            case ContentType.PROJECT_EXPERIENCE:
                ProjectExperience projectExperience = ((ProjectExperience) contentItem);
                startDt = projectExperience.getStartDt();
                endDt = projectExperience.getEndDt();
                break;

            default: break;
        }
        return new ContentItemDto(contentType,
                title,
                contents,
                startDt,
                endDt,
                tagNames,
                point);

    }
}
