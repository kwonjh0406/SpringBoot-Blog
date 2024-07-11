package com.joon.blog.test;

import org.springframework.web.bind.annotation.*;

@RestController
public class HttpControllerTest {

    @GetMapping("/http/get")
    public String getTest(Member member){
        return "get 요청" +member.getId()+", "+member.getUsername()+", " + member.getPassword()+", " + member.getEmail();
    }

    @PostMapping("/http/post")
    public String postTest(@RequestBody Member member){
        return "post 요청" +member.getId()+", "+member.getUsername()+", " + member.getPassword()+", " + member.getEmail();
    }

    @PutMapping("/http/put")
    public String putTest(@RequestBody Member member){
        return "put 요청" +member.getId()+", "+member.getUsername()+", " + member.getPassword()+", " + member.getEmail();
    }

    @DeleteMapping("/http/delete")
    public String deleteTest(){
        return "delete 요청";
    }
}
