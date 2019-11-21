package me.programmeris.myresume.api.controller;

import lombok.RequiredArgsConstructor;
import me.programmeris.myresume.api.dto.Code;
import me.programmeris.myresume.api.dto.Response;
import me.programmeris.myresume.api.service.ResumeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/resumes")
public class ResumeApiController {

    private final ResumeService resumeService;

    @GetMapping("{resume_id:.+}")
    public Response getResume(@PathVariable("resume_id") Long resumeId) {

        return Response.create(Code.SUCCESS, resumeService.getResume(resumeId));
    }

    @GetMapping("/id/{direct_access_id:.+}")
    public Response getResumeByDirectAccessId(@PathVariable("direct_access_id") String directAccessId) {
        return Response.create(Code.SUCCESS, resumeService.getResumeByDirectAccessId(directAccessId));
    }
}
