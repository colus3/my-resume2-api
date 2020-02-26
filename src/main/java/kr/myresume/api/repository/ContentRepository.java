package kr.myresume.api.repository;

import kr.myresume.api.entity.content.Content;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentRepository extends JpaRepository<Content, Long> {

    @EntityGraph(attributePaths = "user")
    Page<Content> findAllByUser_Email(String email, Pageable pageable);

    @EntityGraph(attributePaths = "user")
    Page<Content> findAllByUser_Id(Long id, Pageable pageable);
}
