package me.programmeris.myresume.api.service;

import me.programmeris.myresume.api.dto.response.ContentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContentService {
    ContentDto getContent(Long id);

    Page<ContentDto> getContentsByEmail(String email, Pageable pageable);

    Page<ContentDto> getContentsById(Long id, Pageable pageable);
}
