package me.programmeris.myresume.api.repository;

import me.programmeris.myresume.api.entity.resume.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepository extends JpaRepository<Resume, Long> {

    Resume findOneByDirectAccessId(String directAccessId);
}
