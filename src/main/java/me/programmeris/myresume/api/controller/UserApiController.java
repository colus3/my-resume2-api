package me.programmeris.myresume.api.controller;

import lombok.RequiredArgsConstructor;
import me.programmeris.myresume.api.dto.Code;
import me.programmeris.myresume.api.dto.Response;
import me.programmeris.myresume.api.entity.user.User;
import me.programmeris.myresume.api.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/users")
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @GetMapping("/email/{email:.+}")
    public Response getUser(@PathVariable String email) {
        return Response.create(Code.SUCCESS, userService.getUser(email));
    }

    @GetMapping("{id:[0-9]+}")
    public Response getUser(@PathVariable Long id) {
        return Response.create(Code.SUCCESS, userService.getUser(id));
    }

}
