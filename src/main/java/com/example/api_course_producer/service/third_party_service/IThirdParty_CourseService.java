package com.example.api_course_producer.service.third_party_service;

import com.example.api_course_producer.dto.TPAChapterResponse;
import com.example.api_course_producer.dto.TPACourseResponse;
import com.example.api_course_producer.dto.TPALessonResponse;
import com.example.api_course_producer.dto.TPA_CourseRequest;
import com.example.api_course_producer.entity.course.Course;
import com.example.api_course_producer.entity.course.Lesson;
import com.example.api_course_producer.entity.third_party_app.ThirdParty_Course;
import com.example.api_course_producer.repository.ThirdParty_CourseRepository;
import com.example.api_course_producer.service.download.CourseDownloadService;
import com.example.api_course_producer.service.download.LessonDownloadService;
import com.example.api_course_producer.service.token.JwtService;
import com.example.api_course_producer.service.token.TPATokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class IThirdParty_CourseService implements ThirdParty_CourseService{

    @Autowired
    CourseDownloadService courseDownloadService;

    @Autowired
    LessonDownloadService lessonDownloadService;

    @Autowired
    TPATokenService tpaTokenService;

    @Autowired
    ThirdPartyService thirdPartyService;

    @Autowired
    ThirdParty_CourseRepository thirdParty_courseRepository;

    @Autowired
    JwtService jwtService;

    @Override
    public TPACourseResponse giveCourseByIdToTPA(String token,int id) {
        Boolean check = tpaTokenService.isTokenValid(token,id);
        Course course;
        if(check) {
            course = courseDownloadService.getCourseById(id);
            List<TPAChapterResponse> tpaChapterResponses = course.getChapters().stream().map(chapter -> {
                return TPAChapterResponse.builder().description(chapter.getDescription())
                        .title(chapter.getTitle())
                        .lessons(chapter.getLessons().stream().map(lesson -> {
                            return TPALessonResponse.builder().title(lesson.getTitle())
                                    .description(lesson.getDescription())
                                    .lesson_id(lesson.getId())
                                    .build();
                        }).collect(Collectors.toList())).build();
                }).collect(Collectors.toList());
            TPACourseResponse courseResponse = TPACourseResponse.builder()
                    .course_id(course.getId())
                    .title(course.getTitle())
                    .description(course.getDescription())
                    .author(course.getAuthor())
                    .imageUrl(course.getImageUrl())
                    .topic(course.getTopic())
                    .chapters(tpaChapterResponses)
                    .build();

            return courseResponse;
        }else {
            return null;
        }
    }

    @Override
    public Lesson giveLessonByIdToTPA(String token,int course_id,int id) {
        Boolean check = tpaTokenService.isTokenValid(token,course_id);
        Lesson lesson;
        if(check) {
            lesson= lessonDownloadService.getLessonbyId(id);
            return lesson;
        }else {
            return null;
        }
    }

    @Override
    public ThirdParty_Course addTPACourse(TPA_CourseRequest tpa_course) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 2);

        ThirdParty_Course thirdParty_course = ThirdParty_Course.builder()
                .course(courseDownloadService.getCourseById(tpa_course.course_id()))
                .thirdPartyApplication(thirdPartyService.getById(tpa_course.tpa_id()))
                .startDate(tpa_course.start_date())
                .endDate(tpa_course.end_date())
                .build();
        thirdParty_courseRepository.save(thirdParty_course);

        return thirdParty_course;
    }


    @Override
    public String generateToken(int id) {
        ThirdParty_Course thirdParty_course = thirdParty_courseRepository.findById(id).orElse(null);
        Map<String,Object> cl = new HashMap<>();
        cl.put("course",thirdParty_course.getCourse().getId());
        cl.put("tpa",thirdParty_course.getThirdPartyApplication().getId());

        String jwt = jwtService.generateAccessTokenForTPS(cl,thirdParty_course);

        return jwt;
    }

    @Override
    public List<ThirdParty_Course> getTPAByStatus(int status) {
        if(status ==1 ){
            return thirdParty_courseRepository.getAllValid();
        } else if (status==2) {
            return thirdParty_courseRepository.getAllPending();
        }
        else{
            return thirdParty_courseRepository.getAllInValid();
        }
    }

    @Override
    public ThirdParty_Course find(int id) {
        return thirdParty_courseRepository.findById(id).orElse(null);
    }
}

