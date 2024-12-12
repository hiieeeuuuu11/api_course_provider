package com.example.api_course_producer.service;

import com.example.api_course_producer.dto.LearnerResponse;
import com.example.api_course_producer.dto.ProviderResponse;
import com.example.api_course_producer.repository.CourseRepository;
import com.example.api_course_producer.repository.EnrollmentRepository;
import com.example.api_course_producer.repository.LearnerRepository;
import com.example.api_course_producer.repository.ProviderRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {
  private final ProviderRepository providerRepository;
  private final CourseRepository courseRepository;
  private final EnrollmentRepository enrollmentRepository;
  private final LearnerRepository learnerRepository;


  public long getNumberCourse() {
    return courseRepository.count();
  }

  public long getNumberProvider() {
    return providerRepository.count();
  }


  public long getNumberEnroll() {
    return enrollmentRepository.count();
  }


  public List<LearnerResponse> getTopLearners() {
    List<Object[]> courses = enrollmentRepository.findTopLearnersByEnrollmentCount();
    List<LearnerResponse> learnerResponses = new ArrayList<>();

    for (Object[] course : courses) {
      learnerResponses.add(LearnerResponse.builder()
          .name((String) course[0])
          .course_count(Math.toIntExact((Long) course[1]))
          .build());
    }

    return learnerResponses;
  }


  public List<ProviderResponse> getTopProviders() {
    List<Object[]> providers = providerRepository.findTopProvidersByCourseCount();
    List<ProviderResponse> providerResponses = new ArrayList<>();

    for (Object[] provider : providers) {
      providerResponses.add(ProviderResponse.builder()
          .name((String) provider[0])
          .course_count(Math.toIntExact((Long) provider[1]))
          .build());
    }

    return providerResponses;
  }


  public long getNumberLearner() {
    return learnerRepository.count();
  }




}
