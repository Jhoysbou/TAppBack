package com.tapp.api.services;

import com.tapp.api.models.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TAppController {
    private UserService userService = new UserService();

    @GetMapping("/")
    String helloWorld() {
        return "hello, World!";
    }

    @GetMapping("/save_user")
    User saveUser(@RequestParam(name = "id", required = true) long id,
                  @RequestParam(name = "level") int level,
                  @RequestParam(name = "age") int age) {
        User user = new User(id, level, age, "noSchool");

        userService.saveUser(user);
        return user;
    }

}
