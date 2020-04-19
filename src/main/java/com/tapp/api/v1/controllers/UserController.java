package com.tapp.api.v1.controllers;

import com.google.gson.Gson;
import com.tapp.api.v1.models.User;
import com.tapp.api.v1.services.UserService;
import org.apache.coyote.Response;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/users")
public class UserController {
    private UserService userService = new UserService();
    Gson gson = new Gson();

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
                  @RequestParam int level,
                  @RequestParam int age,
                  @RequestParam String school) {
        User user = new User(id, level, age, school);

        userService.saveUser(user);
    }
    
    @DeleteMapping("{id}")
    void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }

}
