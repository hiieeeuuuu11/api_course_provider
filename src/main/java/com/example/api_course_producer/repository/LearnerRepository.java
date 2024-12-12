package com.example.api_course_producer.repository;

import com.example.api_course_producer.entity.course.Enrollment;
import com.example.api_course_producer.entity.course.Learner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LearnerRepository extends JpaRepository<Learner,Integer> {

}
