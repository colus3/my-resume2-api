package me.programmeris.myresume.api.controller;

import lombok.RequiredArgsConstructor;
import me.programmeris.myresume.api.dto.Code;
import me.programmeris.myresume.api.dto.response.ContentDto;
import me.programmeris.myresume.api.dto.response.Response;
import me.programmeris.myresume.api.service.ContentService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/contents")
public class ContentApiController {

    private final ContentService contentService;

    @GetMapping("{id:.+}")
    public Response<ContentDto> getContent(@PathVariable("id") Long id) {

        return Response.create(Code.SUCCESS, contentService.getContent(id));
    }

    @GetMapping("/email/{email:.+@.+}")
    public Response<ContentDto> getContentsByEmailWithPaging(@PathVariable("email") String email,
                                                            @PageableDefault Pageable pageable) {

        return Response.create(Code.SUCCESS, contentService.getContentsByEmail(email, pageable));
    }
}
