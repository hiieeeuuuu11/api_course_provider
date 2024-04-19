package com.example.api_course_producer.repository;

import com.example.api_course_producer.model.course.Course;
import com.example.api_course_producer.model.third_party_app.ThirdPartyApplication;
import com.example.api_course_producer.model.third_party_app.ThirdParty_Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ThirdParty_CourseRepository extends JpaRepository<ThirdParty_Course,Integer> {

    @Query("select t from ThirdParty_Course t where t.course = :course and t.thirdPartyApplication = :thirdPartyApplication")
    List<ThirdParty_Course> findValid(Course course, ThirdPartyApplication thirdPartyApplication);

    @Query(value = "SELECT * FROM thirdparty_course WHERE end_date >= NOW()",nativeQuery = true)
    List<ThirdParty_Course> getAllValid();

    @Query(value = "SELECT * FROM thirdparty_course WHERE end_date < NOW()",nativeQuery = true)
    List<ThirdParty_Course> getAllInValid();

    @Query(value = "SELECT * FROM thirdparty_course WHERE start_date > NOW()",nativeQuery = true)
    List<ThirdParty_Course> getAllPending();

}
