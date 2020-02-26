package kr.myresume.api.service.impl;

import kr.myresume.api.repository.ResumeRepository;
import lombok.RequiredArgsConstructor;
import kr.myresume.api.dto.response.ResumeDto;
import kr.myresume.api.service.ResumeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository resumeRepository;

    @Override
    public ResumeDto getResume(Long id, String useYn) {
        return ResumeDto.of(resumeRepository.findOneByIdAndUseYn(id, (StringUtils.isEmpty(useYn) ? "Y" : useYn)));
    }

    @Override
    public ResumeDto getResumeByDirectAccessId(String directAccessId, String useYn) {
        return ResumeDto.of(resumeRepository.findOneByDirectAccessIdAndUseYn(directAccessId, (StringUtils.isEmpty(useYn) ? "Y" : useYn)));
    }

    @Override
    public Page<ResumeDto> getResumeByUserId(Long id, Pageable pageable) {
        return resumeRepository.findAllByUser_Id(id, pageable).map(ResumeDto::of);
    }

    @Override
    public Page<ResumeDto> getResumeByUserEmail(String email, Pageable pageable) {
        return resumeRepository.findAllByUser_Email(email, pageable).map(ResumeDto::of);
    }
}
