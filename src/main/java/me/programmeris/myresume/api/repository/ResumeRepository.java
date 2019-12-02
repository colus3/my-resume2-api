package me.programmeris.myresume.api.repository;

import me.programmeris.myresume.api.entity.resume.Resume;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepository extends JpaRepository<Resume, Long> {

    // User 정보까지 한번에 조회 하기 위해 EntityGraph (Fetch join) 사용
    @EntityGraph(attributePaths = "user")
    Resume findOneByIdAndUseYn(Long id, String useYn);
    @EntityGraph(attributePaths = "user")
    Resume findOneByDirectAccessIdAndUseYn(String directAccessId, String useYn);

    @EntityGraph(attributePaths = {"user", "resumeContents"})
    Page<Resume> findAllByUser_Email(String email, Pageable pageable);

    @EntityGraph(attributePaths = {"user", "resumeContents"})
    Page<Resume> findAllByUser_Id(Long id, Pageable pageable);
}
