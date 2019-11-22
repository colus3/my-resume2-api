package me.programmeris.myresume.api.repository;

import me.programmeris.myresume.api.entity.resume.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepository extends JpaRepository<Resume, Long> {

    Resume findOneByIdAndUseYn(Long id, String useYn);
    Resume findOneByDirectAccessIdAndUseYn(String directAccessId, String useYn);
}
