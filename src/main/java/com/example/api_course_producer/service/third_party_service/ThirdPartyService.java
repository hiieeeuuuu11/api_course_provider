package com.example.api_course_producer.service.third_party_service;

import com.example.api_course_producer.model.course.Course;
import com.example.api_course_producer.model.course.Lesson;
import com.example.api_course_producer.model.third_party_app.ThirdPartyApplication;


public interface ThirdPartyService {


    ThirdPartyApplication getById(int id);

    ThirdPartyApplication save(ThirdPartyApplication thirdPartyApplication);

    ThirdPartyApplication findByAddress(String address);

    ThirdPartyApplication add(ThirdPartyApplication thirdPartyApplication);

}
