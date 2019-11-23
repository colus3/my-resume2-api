package me.programmeris.myresume.api.service.impl;

import lombok.RequiredArgsConstructor;
import me.programmeris.myresume.api.dto.response.ResumeDto;
import me.programmeris.myresume.api.entity.resume.Resume;
import me.programmeris.myresume.api.repository.ResumeRepository;
import me.programmeris.myresume.api.service.ResumeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository resumeRepository;

    @Override
    public ResumeDto getResume(Long id, String useYn) {
        Resume resume = resumeRepository.findOneByIdAndUseYn(id, (StringUtils.isEmpty(useYn) ? "Y" : useYn));

        return ResumeDto.of(resume);
    }

    @Override
    public ResumeDto getResumeByDirectAccessId(String directAccessId, String useYn) {
        Resume resume = resumeRepository.findOneByDirectAccessIdAndUseYn(directAccessId, (StringUtils.isEmpty(useYn) ? "Y" : useYn));

        return ResumeDto.of(resume);
    }
}
