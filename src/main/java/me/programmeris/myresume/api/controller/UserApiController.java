package me.programmeris.myresume.api.controller;

import lombok.RequiredArgsConstructor;
import me.programmeris.myresume.api.dto.Code;
import me.programmeris.myresume.api.dto.response.Empty;
import me.programmeris.myresume.api.dto.response.Response;
import me.programmeris.myresume.api.dto.response.UserDto;
import me.programmeris.myresume.api.service.UserService;
import org.springframework.web.bind.annotation.*;

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

        userService.insertUser(userDto);
        return Response.create(Code.SUCCESS);
    }

}
