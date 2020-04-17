package com.tapp.api.v1.services;

import com.google.gson.Gson;
import com.tapp.api.v1.models.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TAppController {
    private UserService userService = new UserService();
    Gson gson = new Gson();

    @GetMapping("/")
    String helloWorld() {
        return "hello, World!";
    }

    @GetMapping("/get_user")
    String getUser(@RequestParam(name = "id", required = true, defaultValue = "0") long id) {
        System.out.println(gson.toJson(userService.getUser(id)));
        return "success";
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
