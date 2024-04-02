package com.example.api_course_producer.service.token;

import com.example.api_course_producer.model.course.Course;
import com.example.api_course_producer.model.third_party_app.ThirdPartyApplication;
import com.example.api_course_producer.model.third_party_app.ThirdParty_Course;
import com.example.api_course_producer.repository.ThirdPartyAppRepository;
import com.example.api_course_producer.repository.ThirdParty_CourseRepository;
import com.example.api_course_producer.service.download.CourseDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TPATokenService {

    @Autowired
    CourseDownloadService downloadService;

    @Autowired
    jwtService jwtService;

    @Autowired
    ThirdPartyAppRepository thirdPartyAppRepository;

    @Autowired
    ThirdParty_CourseRepository thirdParty_courseRepository;

    public boolean isTokenValid(String token){
        int tpaid = jwtService.extractTPAId(token);
        int courseid = jwtService.extractCourseId(token);
        Course course1 = downloadService.getCourseById(courseid);
        ThirdPartyApplication thirdPartyApplication = thirdPartyAppRepository.getReferenceById(tpaid);

        List<ThirdParty_Course> thirdParty_course = thirdParty_courseRepository.findValid(course1,thirdPartyApplication);

        if(!jwtService.isTokenExpried(token) && thirdParty_course.size() > 0){
            return true;
        }
        else return false;
    }


}
