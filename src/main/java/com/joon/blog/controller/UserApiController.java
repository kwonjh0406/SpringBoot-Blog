package com.joon.blog.controller;


import com.joon.blog.model.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {

    @PostMapping("/api/user")
    public int save(@RequestBody User user) {
        System.out.println("api user 호출 완료");
        return 1;
    }
}
