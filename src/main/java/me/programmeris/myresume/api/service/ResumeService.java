package me.programmeris.myresume.api.service;

import me.programmeris.myresume.api.dto.response.ResumeDto;

public interface ResumeService {
    ResumeDto getResume(Long id, String useYn);
    ResumeDto getResumeByDirectAccessId(String directAccessId, String useYn);
}
