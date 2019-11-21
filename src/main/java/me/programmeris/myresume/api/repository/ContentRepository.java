package me.programmeris.myresume.api.repository;

import me.programmeris.myresume.api.entity.content.Content;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentRepository extends JpaRepository<Content, Long> {
}
