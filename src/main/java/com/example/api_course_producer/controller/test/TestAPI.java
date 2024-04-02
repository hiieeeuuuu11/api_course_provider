package com.example.api_course_producer.controller.test;

import com.example.api_course_producer.dto.ChapterRequest;
import com.example.api_course_producer.dto.CourseRequest;
import com.example.api_course_producer.dto.LessonRequest;
import com.example.api_course_producer.model.course.Chapter;
import com.example.api_course_producer.model.course.Course;
import com.example.api_course_producer.model.course.Lesson;
import com.example.api_course_producer.model.third_party_app.ThirdPartyApplication;
import com.example.api_course_producer.model.third_party_app.ThirdParty_Course;
import com.example.api_course_producer.repository.CourseRepository;
import com.example.api_course_producer.repository.ThirdParty_CourseRepository;
import com.example.api_course_producer.service.cloud.S3Service;
import com.example.api_course_producer.service.third_party_service.ThirdPartyService;
import com.example.api_course_producer.service.token.jwtService;
import com.example.api_course_producer.service.upload.ChapterUploadService;
import com.example.api_course_producer.service.upload.LessonUploadService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
@CrossOrigin("*")
public class TestAPI {

    @Autowired
    S3Service s3service;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    ChapterUploadService chapterUploadService;

    @Autowired
    LessonUploadService lessonUploadService;

    @Autowired
    jwtService jwtService;

    @Autowired
    ThirdParty_CourseRepository thirdParty_courseRepository;

    @Autowired
    ThirdPartyService thirdPartyService;

    @GetMapping("/1")
    public ResponseEntity<String> test1(HttpEntity<String> httpEntity) {
        String requestBody = httpEntity.getBody();
        HttpHeaders headers = httpEntity.getHeaders();
        courseRepository.save(Course.builder().title("demo").isPublished(0).build());
        // Xử lý yêu cầu và tạo phản hồi
        return ResponseEntity.ok("Response");
    }

    @GetMapping("/2")
    public ResponseEntity<String> test2(@RequestBody Course course) {
        System.out.println(course);
        courseRepository.save(course);
        // Xử lý yêu cầu và tạo phản hồi
        return ResponseEntity.ok("Response");
    }

    @GetMapping("/3")
    public ResponseEntity<Course> test3(@RequestParam("title") String title, @RequestParam("imageUrl")MultipartFile multipartFile,
                                        @RequestParam("isPublished") int isPublished) {
        String imgUrl = s3service.uploadFile(multipartFile,"logo");
        Course course = Course.builder().title(title)
                .isPublished(isPublished)
                .imageUrl(imgUrl)
                .build();
        courseRepository.save(course);
        return ResponseEntity.ok(course);
    }

    @PostMapping("/4")
    public ResponseEntity<Course> test4(@ModelAttribute CourseRequest courseDetailInformation) {
        String imgUrl = s3service.uploadFile(courseDetailInformation.getMultipartFile(),"logo");
        Course course = Course.builder()
                .id(courseDetailInformation.getId())
                .title(courseDetailInformation.getTitle())
                .description(courseDetailInformation.getDescription())
                .imageUrl(imgUrl)
                .isPublished(courseDetailInformation.getIsPublished())
                .build();
        courseRepository.save(course);
        return ResponseEntity.ok(course);
    }

    @GetMapping("/5")
    public ResponseEntity<Chapter> test5(@ModelAttribute ChapterRequest chapterDetailInfomation) {
        Chapter chapter = chapterUploadService.addChapterToCourse(chapterDetailInfomation);
        return ResponseEntity.ok(chapter);
    }

    @GetMapping("/6")
    public ResponseEntity<Map<String,String>> test6(@RequestParam("type") String type) {
        Map<String,String> response = lessonUploadService.getPresignUrlFromS3(type);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/7")
    public ResponseEntity<Lesson> test7(@RequestBody LessonRequest lessonRequest) {
        Lesson response = lessonUploadService.addLessonToChapter(lessonRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/8")
    public ResponseEntity<String> test8() {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 2);
        Map<String,Object> cl = new HashMap<>();
        cl.put("course",1);
        ThirdParty_Course thirdParty_course = ThirdParty_Course.builder()
                .course(courseRepository.getReferenceById(1))
                .thirdPartyApplication(thirdPartyService.getById(1))
                .startDate(new Date())
                .endDate(calendar.getTime())
                .build();
        thirdParty_courseRepository.save(thirdParty_course);
        cl.put("tpa",1);
        String jwt = jwtService.generateAccessTokenForTPS(cl,thirdParty_course);
        Date app = jwtService.extractExpiration(jwt);

        int course = jwtService.extractCourseId(jwt);
        return ResponseEntity.ok(jwt);
    }


//    @GetMapping("/9")
//    public ResponseEntity<String> test9(@RequestBody LessonRequest lessonRequest) {
//        Lesson response = lessonUploadService.addLessonToChapter(lessonRequest);
//        return ResponseEntity.ok(response);
//    }


}
