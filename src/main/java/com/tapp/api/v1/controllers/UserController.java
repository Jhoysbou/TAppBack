package com.tapp.api.v1.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.tapp.api.v1.models.User;
import com.tapp.api.v1.services.UserService;

import com.tapp.api.v1.utils.IdWrapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/users")
public class UserController {
    private UserService userService = new UserService();
    private final Gson gson = new Gson();
    private final ObjectMapper mapper = new ObjectMapper();

    @GetMapping
    String getAllUsers() {
        return gson.toJson(userService.getAllUsers());
    }

    @GetMapping("{id}")
    String getUser(@PathVariable long id) {
        return gson.toJson(userService.getUser(id));
    }

    @PutMapping("{id}")
    void saveUser(@PathVariable long id,
            @RequestBody User user) {
        user.setId(id);
        userService.saveUser(user);
    }

    @PatchMapping("{id}")
    void updateUser(@PathVariable long id,
                    @RequestBody IdWrapper idWrapper) {

        userService.addTest(id, idWrapper.getId());
    }

    @DeleteMapping("{id}")
    void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }

}
