package com.example.api_course_producer.controller.user;

import com.example.api_course_producer.entity.user.AppUser;
import com.example.api_course_producer.service.token.JwtService;
import com.example.api_course_producer.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AppUser appUser){
        AppUser appUser1 = userService.loadAppUser(appUser.getUsername(),appUser.getPassword());
        String token = jwtService.generateToken(appUser1);
        return ResponseEntity.ok(token);
    }

}
