package com.microservice.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import com.microservice.user.VO.ResponseTemplateVO;
import com.microservice.user.entity.User;
import com.microservice.user.service.UserService;

import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public User saveUser(@RequestBody User user) {
        //log.info("Inside saveUser of UserController");
        System.out.println("Inside saveUser of UserController");
        return userService.saveUser(user);
    }

    @GetMapping("/{id}")
    public ResponseTemplateVO getUserWithDepartment(@PathVariable("id") Long userId) {
        //log.info("Inside getUserWithDepartment of UserController");
        System.out.println("Inside getUserWithDepartment of UserController");
        return userService.getUserWithDepartment(userId);
    }


}