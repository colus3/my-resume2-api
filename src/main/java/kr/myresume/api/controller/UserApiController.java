package kr.myresume.api.controller;

import lombok.RequiredArgsConstructor;
import kr.myresume.api.dto.Code;
import kr.myresume.api.dto.request.SignUpForm;
import kr.myresume.api.dto.response.Empty;
import kr.myresume.api.dto.response.Response;
import kr.myresume.api.dto.response.UserDto;
import kr.myresume.api.service.UserService;
import kr.myresume.api.session.annotation.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/api/v2/users")
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @Session
    @GetMapping("/email/{email:.+}")
    public Response<UserDto> getUser(@PathVariable String email) {

        UserDto user = userService.getUser(email);
        return Response.create(Code.SUCCESS, user);
    }

    @Session
    @GetMapping("{id:[0-9]+}")
    public Response<UserDto> getUser(@PathVariable Long id) {

        UserDto user = userService.getUser(id);
        return Response.create(Code.SUCCESS, user);
    }

    @Session
    @PostMapping("")
    public Response<Empty> addUser(SignUpForm signUpForm) {

        userService.addUser(signUpForm);
        return Response.create(Code.SUCCESS);
    }

    @Session
    @PutMapping("/email/{email:.+}")
    public Response<Empty> editUser(@PathVariable("email") String email,
                                    @RequestBody SignUpForm signUpForm) {

        userService.editUser(email, signUpForm);
        return Response.create(Code.SUCCESS);
    }

    @Session
    @GetMapping("")
    public Response<UserDto> getUsers(@RequestParam(name = "email", required = false) String email,
                                      @PageableDefault(size = 20) Pageable pageable) {

        Page<UserDto> users = userService.getUsers(email, pageable);
        return Response.create(Code.SUCCESS, users);
    }
}
