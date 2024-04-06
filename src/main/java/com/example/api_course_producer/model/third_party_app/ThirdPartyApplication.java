package com.example.api_course_producer.model.third_party_app;

import com.example.api_course_producer.model.course.Course;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "thirdpartyapp")
@Entity
@Builder
public class ThirdPartyApplication {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String appName;

    String appAddress;

    String email;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "thirdPartyApplication")
    @JsonIgnore
    List<ThirdParty_Course> thirdParty_course;

}
