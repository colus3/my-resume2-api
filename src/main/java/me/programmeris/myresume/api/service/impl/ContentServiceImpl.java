package me.programmeris.myresume.api.service.impl;

import lombok.RequiredArgsConstructor;
import me.programmeris.myresume.api.dto.response.ContentDto;
import me.programmeris.myresume.api.repository.ContentRepository;
import me.programmeris.myresume.api.service.ContentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Override
    public Page<ContentDto> getContentsByEmail(String email, Pageable pageable) {
        return contentRepository.findAllByUser_Email(email, pageable).map(ContentDto::of);
    }

    @Override
    public Page<ContentDto> getContentsById(Long id, Pageable pageable) {
        return contentRepository.findAllByUser_Id(id, pageable).map(ContentDto::of);
    }
}
