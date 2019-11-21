package me.programmeris.myresume.api.service;

import me.programmeris.myresume.api.entity.resume.Resume;

public interface ResumeService {
    Resume getResume(Long id);
    Resume getResumeByDirectAccessId(String directAccessId);
}
