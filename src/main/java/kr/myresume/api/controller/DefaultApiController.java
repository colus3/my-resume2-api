package kr.myresume.api.controller;

import kr.myresume.api.dto.Code;
import kr.myresume.api.dto.response.Empty;
import kr.myresume.api.dto.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class DefaultApiController {

    @GetMapping("")
    public Response<Empty> get() {
        return Response.create(Code.SUCCESS);
    }
}
