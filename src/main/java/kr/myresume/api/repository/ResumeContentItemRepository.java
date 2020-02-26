package kr.myresume.api.repository;

import kr.myresume.api.entity.resume.ResumeContentItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeContentItemRepository extends JpaRepository<ResumeContentItem, Long> {
}
