package com.example.api_course_producer.model.third_party_app;

import com.example.api_course_producer.model.course.Course;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @ManyToOne @JoinColumn(name = "course") @JsonBackReference
    Course course;

    Date startDate;

    Date endDate;

}
