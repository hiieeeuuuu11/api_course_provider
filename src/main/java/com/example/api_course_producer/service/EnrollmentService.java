package com.example.api_course_producer.service;

import com.example.api_course_producer.entity.course.Enrollment;
import com.example.api_course_producer.repository.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EnrollmentService {
  private final EnrollmentRepository enrollmentRepository;

  public List<Enrollment> getAllEnrollmentsByCourseId(List<Integer> courseIds) {
    return enrollmentRepository.getAllByCourseIdIn(courseIds);
  }



}
