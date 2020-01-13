package me.programmeris.myresume.api.service.impl;

import lombok.RequiredArgsConstructor;
import me.programmeris.myresume.api.dto.response.ContentDto;
import me.programmeris.myresume.api.entity.content.Content;
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

    @Override
    @Transactional
    public void addContent(ContentDto contentDto) {

        Content content = contentDto.toEntity();
        contentRepository.save(content);
    }

    @Override
    @Transactional
    public void editContent(Long id, ContentDto contentDto) {

        Content content = contentRepository.findById(id).orElse(null);
        if (content == null) {
            // TODO: 예외 처리 관련해서 새로 정리 해야 함.
            throw new RuntimeException();
        }

        content.setName(contentDto.getName());
        content.setDisplayOrder(contentDto.getDisplayOrder());
        // TODO: ContentItem 추가하는 로직 넣아야 함.
    }
}
