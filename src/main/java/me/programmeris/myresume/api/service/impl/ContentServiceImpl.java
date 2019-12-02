package me.programmeris.myresume.api.service.impl;

import lombok.RequiredArgsConstructor;
import me.programmeris.myresume.api.dto.response.ContentDto;
import me.programmeris.myresume.api.entity.content.Content;
import me.programmeris.myresume.api.repository.ContentRepository;
import me.programmeris.myresume.api.service.ContentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ContentServiceImpl implements ContentService {

    private final ContentRepository contentRepository;

    @Override
    public ContentDto getContent(Long id) {
        return ContentDto.of(contentRepository.findById(id).orElse(null));
    }
}
