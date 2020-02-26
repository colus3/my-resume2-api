package kr.myresume.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import kr.myresume.api.entity.resume.Resume;
import kr.myresume.api.entity.resume.ResumeType;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter
public class ResumeDto implements ResponseData {

    private String id;
    private ResumeType type;
    private String name;
    private String shortIntro;
    private String useYn;
    private String resumeUrl;
    private LocalDateTime createDt;

    @JsonProperty(value = "user")
    private UserDto userDto;

    @JsonProperty(value = "resumeContents")
    private List<ResumeContentDto> resumeContentDtos;

    public ResumeDto(String id, ResumeType type, String name, String shortIntro, String useYn, String resumeUrl, LocalDateTime createDt, UserDto userDto, List<ResumeContentDto> resumeContentDtos) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.shortIntro = shortIntro;
        this.useYn = useYn;
        this.resumeUrl = resumeUrl;
        this.createDt = createDt;
        this.userDto = userDto;
        this.resumeContentDtos = resumeContentDtos;
    }

    public static ResumeDto of(Resume resume) {
        if (resume == null) return null;

        return new ResumeDto(resume.getDirectAccessId(),
                resume.getType(),
                resume.getName(),
                resume.getShortIntro(),
                resume.getUseYn(),
                resume.getResumeShortUrl(),
                resume.getCreateDt(),
                UserDto.of(resume.getUser()),
                resume.getResumeContents().stream().map(ResumeContentDto::of).collect(toList())
        );
    }
}
