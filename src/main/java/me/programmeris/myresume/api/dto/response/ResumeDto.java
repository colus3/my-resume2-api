package me.programmeris.myresume.api.dto.response;

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

    @JsonProperty(value = "user")
    private UserDto userDto;

    @JsonProperty(value = "resume_contents")
    private List<ResumeContentDto> resumeContentDtos;

    public ResumeDto(String id, ResumeType type, String name, String useYn, String resumeUrl, UserDto userDto, List<ResumeContentDto> resumeContentDtos) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.useYn = useYn;
        this.resumeUrl = resumeUrl;
        this.userDto = userDto;
        this.resumeContentDtos = resumeContentDtos;
    }

    public static ResumeDto of(Resume resume) {
        if (resume == null) return null;

        return new ResumeDto(resume.getDirectAccessId(),
                resume.getResumeType(),
                resume.getResumeName(),
                resume.getUseYn(),
                resume.getResumeShortUrl(),
                UserDto.of(resume.getUser()),
                resume.getResumeContents().stream().map(ResumeContentDto::of).collect(toList()));
    }
}
