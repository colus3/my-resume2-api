package me.programmeris.myresume.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import me.programmeris.myresume.api.entity.resume.Resume;
import me.programmeris.myresume.api.entity.resume.ResumeType;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter
public class ResumeDto implements ResponseData {

    private String id;
    private ResumeType type;
    private String name;
    private String useYn;
    private String resumeUrl;

    @JsonProperty(value = "contents")
    private List<ResumeContentDto> resumeContentDtos;

    public ResumeDto(String id, ResumeType type, String name, String useYn, String resumeUrl, List<ResumeContentDto> resumeContentDtos) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.useYn = useYn;
        this.resumeUrl = resumeUrl;
        this.resumeContentDtos = resumeContentDtos;
    }

    public static ResumeDto of(Resume resume) {

        return new ResumeDto(resume.getDirectAccessId(),
                resume.getResumeType(),
                resume.getResumeName(),
                resume.getUseYn(),
                resume.getResumeShortUrl(),
                resume.getResumeContents().stream().map(ResumeContentDto::of).collect(toList()));
    }
}
