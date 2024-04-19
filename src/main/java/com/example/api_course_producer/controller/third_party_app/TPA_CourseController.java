package com.example.api_course_producer.controller.third_party_app;

import com.example.api_course_producer.dto.TPACourseResponse;
import com.example.api_course_producer.dto.TPA_CourseRequest;
import com.example.api_course_producer.model.course.Course;
import com.example.api_course_producer.model.course.Lesson;
import com.example.api_course_producer.model.third_party_app.ThirdParty_Course;
import com.example.api_course_producer.service.MailService;
import com.example.api_course_producer.service.download.CourseDownloadService;
import com.example.api_course_producer.service.third_party_service.ThirdPartyService;
import com.example.api_course_producer.service.third_party_service.ThirdParty_CourseService;
import com.example.api_course_producer.service.token.JwtService;
import com.example.api_course_producer.service.token.TPATokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Autowired
    private MailService senderService;

    @GetMapping("/testvalid")
    public ResponseEntity<Boolean> testValid(@RequestHeader("token") String token,@RequestParam("id") int id){
        Boolean check = tpaTokenService.isTokenValid(token,id);
        return ResponseEntity.ok(check);
    }

    @GetMapping("/getcoursebyid")
    public ResponseEntity<TPACourseResponse> getCourseById(@RequestHeader("token") String token, @RequestParam("id") int id){
        TPACourseResponse course = thirdParty_courseService.giveCourseByIdToTPA(token,id);
        return ResponseEntity.ok(course);
    }

    @GetMapping("/getlessonbyid")
    public ResponseEntity<Lesson> getLessonbyId(@RequestHeader("token") String token,@RequestParam("course") int course_id, @RequestParam("id") int id){
        Lesson lesson =thirdParty_courseService.giveLessonByIdToTPA(token,course_id,id);
        return ResponseEntity.ok(lesson);
    }

    @PostMapping("/addtpacourse")
    public ResponseEntity<ThirdParty_Course> addTPACourse(@RequestBody TPA_CourseRequest tpa_course) {

        ThirdParty_Course thirdParty_course = thirdParty_courseService.addTPACourse(tpa_course);
        return ResponseEntity.ok(thirdParty_course);
    }

    @GetMapping("/generatetoken")
    public ResponseEntity<String> genarateToken(@RequestParam("id") int id) {

        String token = thirdParty_courseService.generateToken(id);
        return ResponseEntity.ok(token);
    }

    @GetMapping("/getbystatus")
    public ResponseEntity<List<ThirdParty_Course>> getByStatus(@RequestParam("status") int status) {

        return ResponseEntity.ok(thirdParty_courseService.getTPAByStatus(status));
    }

    @GetMapping("/sendtoken")
    public ResponseEntity<String> sendMail(@RequestParam("id") int id) {
        ThirdParty_Course thirdParty_course = thirdParty_courseService.find(id);

        String token = thirdParty_courseService.generateToken(id);

        String body = "Welcome to our course : "+ thirdParty_course.getCourse().getTitle() +"\n" +
                "This is access token : "+ token + "\n";
        body += "This is course data API : http://localhost:8080/tpa/getcoursebyid?id="+thirdParty_course.getCourse().getId()+" \n" +
                "This is lesson data API : http://localhost:8080/tpa/getlessonbyid?course="+thirdParty_course.getCourse().getId()+"&id=";

        senderService.sendSimpleEmail(thirdParty_course.getThirdPartyApplication().getEmail(),
                "This is access key for your course",
                body);

        return ResponseEntity.ok(token);
    }


}
