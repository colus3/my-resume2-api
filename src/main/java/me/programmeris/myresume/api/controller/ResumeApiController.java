package me.programmeris.myresume.api.controller;

import lombok.RequiredArgsConstructor;
import me.programmeris.myresume.api.dto.Code;
import me.programmeris.myresume.api.dto.response.Response;
import me.programmeris.myresume.api.service.ResumeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/resumes")
public class ResumeApiController {

    private final ResumeService resumeService;

    @GetMapping("{resume_id:.+}")
    public Response getResume(@PathVariable("resume_id") Long resumeId,
                              @RequestParam(value = "use_yn", defaultValue = "Y") String useYn) {

        return Response.create(Code.SUCCESS, resumeService.getResume(resumeId, useYn));
    }

    @GetMapping("/id/{direct_access_id:.+}")
    public Response getResumeByDirectAccessId(@PathVariable("direct_access_id") String directAccessId,
                                              @RequestParam(value = "use_yn", defaultValue = "Y") String useYn) {

        return Response.create(Code.SUCCESS, resumeService.getResumeByDirectAccessId(directAccessId, useYn));
    }
}
