package me.programmeris.myresume.api.repository;

import me.programmeris.myresume.api.entity.resume.ResumeContentItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeContentItemRepository extends JpaRepository<ResumeContentItem, Long> {
}
