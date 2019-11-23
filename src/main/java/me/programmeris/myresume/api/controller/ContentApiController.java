package me.programmeris.myresume.api.controller;

import lombok.RequiredArgsConstructor;
import me.programmeris.myresume.api.dto.Code;
import me.programmeris.myresume.api.dto.response.Response;
import me.programmeris.myresume.api.service.ContentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/contents")
public class ContentApiController {

    private final ContentService contentService;

    @GetMapping("{id:.+}")
    public Response getContent(@PathVariable("id") Long id) {

        return Response.create(Code.SUCCESS, contentService.getContent(id));
    }
}
