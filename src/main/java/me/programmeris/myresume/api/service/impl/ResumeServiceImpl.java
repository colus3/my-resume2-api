package me.programmeris.myresume.api.service.impl;

import lombok.RequiredArgsConstructor;
import me.programmeris.myresume.api.entity.resume.Resume;
import me.programmeris.myresume.api.repository.ResumeRepository;
import me.programmeris.myresume.api.service.ResumeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository resumeRepository;

    @Override
    public Resume getResume(Long id) {
        return resumeRepository.findById(id).orElse(null);
    }

    @Override
    public Resume getResumeByDirectAccessId(String directAccessId) {
        return resumeRepository.findOneByDirectAccessId(directAccessId);
    }
}
