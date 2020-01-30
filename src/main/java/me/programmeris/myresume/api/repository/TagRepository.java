package me.programmeris.myresume.api.repository;

import me.programmeris.myresume.api.entity.content.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {

    Tag findByName(String tagName);
    List<Tag> findAllByNameIn(List<String> tagNames);
}
