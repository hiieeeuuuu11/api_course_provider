package com.example.api_course_producer.entity.third_party_app;

import com.example.api_course_producer.entity.course.Course;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "thirdparty_course")
@Entity
@Builder
public class ThirdParty_Course {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ManyToOne @JoinColumn(name = "thirdPartyApplication")
    ThirdPartyApplication thirdPartyApplication;

    @ManyToOne @JoinColumn(name = "course")
    Course course;

    Date startDate;

    Date endDate;

}
