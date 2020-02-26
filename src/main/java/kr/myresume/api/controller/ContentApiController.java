package kr.myresume.api.controller;

import lombok.RequiredArgsConstructor;
import kr.myresume.api.dto.Code;
import kr.myresume.api.dto.response.ContentDto;
import kr.myresume.api.dto.response.Response;
import kr.myresume.api.service.ContentService;
import kr.myresume.api.session.annotation.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/contents")
public class ContentApiController {

    private final ContentService contentService;

    @Session
    @GetMapping("{id:.+}")
    public Response<ContentDto> getContent(@PathVariable("id") Long id) {

        ContentDto content = contentService.getContent(id);
        return Response.create(Code.SUCCESS, content);
    }

    @Session
    @GetMapping("/email/{email:.+@.+}")
    public Response<ContentDto> getContentsByEmailWithPaging(@PathVariable("email") String email,
                                                            @PageableDefault Pageable pageable) {

        Page<ContentDto> contents = contentService.getContentsByEmail(email, pageable);
        return Response.create(Code.SUCCESS, contents);
    }

    @Session
    @PostMapping("")
    public void addContent(@RequestBody ContentDto contentDto) {

        contentService.addContent(contentDto);
    }

    @Session
    @PutMapping("{id:.+}")
    public void editContent(@PathVariable("id") Long id,
                            @RequestBody ContentDto contentDto) {

        contentService.editContent(id, contentDto);
    }

}
