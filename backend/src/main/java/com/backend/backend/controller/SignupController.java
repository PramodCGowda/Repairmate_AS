package com.backend.backend.controller;

import com.backend.backend.dto.SignupDTO;
import com.backend.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class SignupController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Map<String, Object>> signup(@RequestBody SignupDTO request) {
        return userService.createUser(request.getUsername(), request.getPassword(), request.getEmail());
    }

    @GetMapping("/test")
    public String signup() {
        return "test";
    }
}
