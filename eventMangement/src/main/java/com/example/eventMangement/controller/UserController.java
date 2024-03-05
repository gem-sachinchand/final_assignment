package com.example.eventMangement.controller;

import com.example.eventMangement.dto.UserDto;
import com.example.eventMangement.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/createUser")
    private ResponseEntity<?> createNewUser(@RequestBody @Valid UserDto data){
        return userService.createUser(data);
    }

    @GetMapping("/authToken")
    private ResponseEntity<String> getAuthorizationToken(@RequestBody @Valid UserDto data){
        return userService.generateToken(data);
    }
}