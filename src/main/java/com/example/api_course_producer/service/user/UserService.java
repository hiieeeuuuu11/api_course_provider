package com.example.api_course_producer.service.user;

import com.example.api_course_producer.model.user.AppUser;
import com.example.api_course_producer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public AppUser loadAppUser(String username,String password){
        return userRepository.findAppUserByUsernameAndPassword(username,password).orElse(null);
    }

    public AppUser loadAppUserByUsername(String username){
        return userRepository.findAppUserByUsername(username).orElse(null);
    }

}
