package com.tapp.api.v1.controllers;

import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
@RequestMapping("/")
public class StartupController {
    @GetMapping
    void getStartupParams(HttpServletRequest request) {
        System.out.println(request.getParameter("sign"));
    }
}
