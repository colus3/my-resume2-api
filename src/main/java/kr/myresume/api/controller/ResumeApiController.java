package kr.myresume.api.controller;

import lombok.RequiredArgsConstructor;
import kr.myresume.api.dto.Code;
import kr.myresume.api.dto.response.Response;
import kr.myresume.api.dto.response.ResumeDto;
import kr.myresume.api.service.ResumeService;
import kr.myresume.api.session.annotation.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/resumes")
public class ResumeApiController {

    private final ResumeService resumeService;

    @Session
    @GetMapping("{resume_id:[0-9]+}")
    public Response<ResumeDto> getResume(@PathVariable("resume_id") Long resumeId,
                                         @RequestParam(value = "use_yn", defaultValue = "Y") String useYn) {

        ResumeDto resume = resumeService.getResume(resumeId, useYn);
        return Response.create(Code.SUCCESS, resume);
    }

    @GetMapping("/id/{direct_access_id:.+}")
    public Response<ResumeDto> getResumeByDirectAccessId(@PathVariable("direct_access_id") String directAccessId,
                                                         @RequestParam(value = "use_yn", defaultValue = "Y") String useYn) {

        ResumeDto resume = resumeService.getResumeByDirectAccessId(directAccessId, useYn);
        return Response.create(Code.SUCCESS, resume);
    }

    @Session
    @GetMapping("/user-id/{id:[0-9]+}")
    public Response<ResumeDto> getResumesByUserIdWithPaging(@PathVariable("id") Long id,
                                                   @PageableDefault(sort = "createDt", direction = Sort.Direction.DESC) Pageable pageable) {

        Page<ResumeDto> resumes = resumeService.getResumeByUserId(id, pageable);
        return Response.create(Code.SUCCESS, resumes);
    }

    @Session
    @GetMapping("/email/{email:.+@.+}")
    public Response<ResumeDto> getResumesByUserEmailWithPaging(@PathVariable("email") String email,
                                                              @PageableDefault(sort = "createDt", direction = Sort.Direction.DESC) Pageable pageable) {

        Page<ResumeDto> resumes = resumeService.getResumeByUserEmail(email, pageable);
        return Response.create(Code.SUCCESS, resumes);
    }
}
