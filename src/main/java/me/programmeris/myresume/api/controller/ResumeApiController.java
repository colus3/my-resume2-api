package me.programmeris.myresume.api.controller;

import lombok.RequiredArgsConstructor;
import me.programmeris.myresume.api.dto.Code;
import me.programmeris.myresume.api.dto.response.Response;
import me.programmeris.myresume.api.dto.response.ResumeDto;
import me.programmeris.myresume.api.service.ResumeService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/resumes")
public class ResumeApiController {

    private final ResumeService resumeService;

    @GetMapping("{resume_id:.+}")
    public Response<ResumeDto> getResume(@PathVariable("resume_id") Long resumeId,
                              @RequestParam(value = "use_yn", defaultValue = "Y") String useYn) {

        return Response.create(Code.SUCCESS, resumeService.getResume(resumeId, useYn));
    }

    @GetMapping("/id/{direct_access_id:.+}")
    public Response<ResumeDto> getResumeByDirectAccessId(@PathVariable("direct_access_id") String directAccessId,
                                              @RequestParam(value = "use_yn", defaultValue = "Y") String useYn) {

        return Response.create(Code.SUCCESS, resumeService.getResumeByDirectAccessId(directAccessId, useYn));
    }

    @GetMapping("/user-id/{id:[0-9]+}")
    public Response<ResumeDto> getResumeByUserIdWithPaging(@PathVariable("id") Long id,
                                                   @PageableDefault(sort = "createDt", direction = Sort.Direction.DESC) Pageable pageable) {
        return Response.create(Code.SUCCESS, resumeService.getResumeByUserId(id, pageable));
    }

    @GetMapping("/email/{email:.+@.+}")
    public Response<ResumeDto> getResumeByUserEmailWithPaging(@PathVariable("email") String email,
                                                              @PageableDefault(sort = "createDt", direction = Sort.Direction.DESC) Pageable pageable) {
        return Response.create(Code.SUCCESS, resumeService.getResumeByUserEmail(email, pageable));
    }
}
