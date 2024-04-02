package com.example.api_course_producer.controller.third_party_app;

import com.example.api_course_producer.model.course.Course;
import com.example.api_course_producer.model.course.Lesson;
import com.example.api_course_producer.service.download.CourseDownloadService;
import com.example.api_course_producer.service.third_party_service.ThirdPartyService;
import com.example.api_course_producer.service.third_party_service.ThirdParty_CourseService;
import com.example.api_course_producer.service.token.TPATokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tpa")
@CrossOrigin("*")
public class ThirdPartyAppController {

    @Autowired
    CourseDownloadService courseDownloadService;

    @Autowired
    ThirdPartyService thirdPartyService;

    @Autowired
    ThirdParty_CourseService thirdParty_courseService;

    @Autowired
    TPATokenService tpaTokenService;

    @GetMapping("/testvalid")
    public ResponseEntity<Boolean> testValid( @RequestHeader("token") String token){
        Boolean check = tpaTokenService.isTokenValid(token);
        return ResponseEntity.ok(check);
    }

    @GetMapping("/getcoursebyid")
    public ResponseEntity<Course> getCourseById(@RequestHeader("token") String token,@RequestParam("id") int id){
        Course course = thirdParty_courseService.giveCourseByIdToTPA(token,id);
        return ResponseEntity.ok(course);
    }

    @GetMapping("/getlessonbyid")
    public ResponseEntity<Lesson> getLessonbyId(@RequestHeader("token") String token,@RequestParam("id") int id){
        Lesson lesson =thirdParty_courseService.giveLessonByIdToTPA(token,id);
        return ResponseEntity.ok(lesson);
    }


}
