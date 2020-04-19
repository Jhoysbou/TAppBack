package com.tapp.api.v1.controllers;

import com.tapp.api.v1.models.User;
import com.tapp.api.v1.services.UserService;

import com.tapp.api.v1.utils.IdWrapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/users")
public class UserController {
    private UserService userService = new UserService();

    @GetMapping
    List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("{id}")
    User getUser(@PathVariable long id) {
        return userService.getUser(id);
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
