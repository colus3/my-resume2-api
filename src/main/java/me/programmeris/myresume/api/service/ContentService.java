package me.programmeris.myresume.api.service;

import me.programmeris.myresume.api.dto.response.ContentDto;

public interface ContentService {
    ContentDto getContent(Long id);
}
