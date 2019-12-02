package me.programmeris.myresume.api.service;

import me.programmeris.myresume.api.dto.response.ResumeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ResumeService {
    ResumeDto getResume(Long id, String useYn);
    ResumeDto getResumeByDirectAccessId(String directAccessId, String useYn);

    Page<ResumeDto> getResumeByUserId(Long id, Pageable pageable);
    Page<ResumeDto> getResumeByUserEmail(String email, Pageable pageable);
}
