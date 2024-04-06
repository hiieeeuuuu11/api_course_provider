package com.example.api_course_producer.controller.third_party_app;

import com.example.api_course_producer.dto.TokenRequest;
import com.example.api_course_producer.model.course.Course;
import com.example.api_course_producer.model.course.Lesson;
import com.example.api_course_producer.model.third_party_app.ThirdParty_Course;
import com.example.api_course_producer.repository.CourseRepository;
import com.example.api_course_producer.service.download.CourseDownloadService;
import com.example.api_course_producer.service.third_party_service.ThirdPartyService;
import com.example.api_course_producer.service.third_party_service.ThirdParty_CourseService;
import com.example.api_course_producer.service.token.JwtService;
import com.example.api_course_producer.service.token.TPATokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/tpa")
@CrossOrigin("*")
public class TPA_CourseController {

    @Autowired
    CourseDownloadService courseDownloadService;

    @Autowired
    ThirdPartyService thirdPartyService;

    @Autowired
    ThirdParty_CourseService thirdParty_courseService;

    @Autowired
    TPATokenService tpaTokenService;

    @Autowired
    JwtService jwtService;

    @GetMapping("/testvalid")
    public ResponseEntity<Boolean> testValid(@RequestHeader("token") String token){
        Boolean check = tpaTokenService.isTokenValid(token);
        return ResponseEntity.ok(check);
    }

    @GetMapping("/getcoursebyid")
    public ResponseEntity<Course> getCourseById(@RequestHeader("token") String token, @RequestParam("id") int id){
        Course course = thirdParty_courseService.giveCourseByIdToTPA(token,id);
        return ResponseEntity.ok(course);
    }

    @GetMapping("/getlessonbyid")
    public ResponseEntity<Lesson> getLessonbyId(@RequestHeader("token") String token, @RequestParam("id") int id){
        Lesson lesson =thirdParty_courseService.giveLessonByIdToTPA(token,id);
        return ResponseEntity.ok(lesson);
    }

    @PostMapping("/genaratetoken")
    public ResponseEntity<String> test8(@RequestBody TokenRequest tokenRequest) {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 2);

        ThirdParty_Course thirdParty_course = ThirdParty_Course.builder()
                .course(courseDownloadService.getCourseById(tokenRequest.course_id()))
                .thirdPartyApplication(thirdPartyService.getById(tokenRequest.tpa_id()))
                .startDate(tokenRequest.start_date())
                .endDate(tokenRequest.end_date())
                .build();

        Map<String,Object> cl = new HashMap<>();
        cl.put("course",tokenRequest.course_id());
        cl.put("tpa",tokenRequest.tpa_id());

        String jwt = jwtService.generateAccessTokenForTPS(cl,thirdParty_course);
        Date app = jwtService.extractExpiration(jwt);

        int course = jwtService.extractCourseId(jwt);
        return ResponseEntity.ok(jwt);
    }



}
