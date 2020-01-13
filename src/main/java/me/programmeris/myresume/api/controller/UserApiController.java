package me.programmeris.myresume.api.controller;

import lombok.RequiredArgsConstructor;
import me.programmeris.myresume.api.dto.Code;
import me.programmeris.myresume.api.dto.response.Empty;
import me.programmeris.myresume.api.dto.response.Response;
import me.programmeris.myresume.api.dto.response.UserDto;
import me.programmeris.myresume.api.service.UserService;
import me.programmeris.myresume.api.session.annotation.Session;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v2/users")
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @GetMapping("/email/{email:.+}")
    public Response<UserDto> getUser(@PathVariable String email) {

        return Response.create(Code.SUCCESS, userService.getUser(email));
    }

    @GetMapping("{id:[0-9]+}")
    public Response<UserDto> getUser(@PathVariable Long id) {

        return Response.create(Code.SUCCESS, userService.getUser(id));
    }

    @PostMapping("")
    public Response<Empty> addUser(UserDto userDto) {

        userService.addUser(userDto);
        return Response.create(Code.SUCCESS);
    }

    @Session
    @PutMapping("/email/{email:.+}")
    public Response<Empty> editUser(@PathVariable("email") String email,
                                    @RequestBody UserDto userDto) {

        userService.editUser(email, userDto);
        return Response.create(Code.SUCCESS);
    }

    @GetMapping("")
    public Response<UserDto> getUsers(@RequestParam(name = "email", required = false) String email,
                                      @PageableDefault(size = 20) Pageable pageable) {
        return Response.create(Code.SUCCESS,
                userService.getUsers(email, pageable));
    }
}
