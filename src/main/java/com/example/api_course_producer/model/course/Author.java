package com.example.api_course_producer.model.course;

import com.example.api_course_producer.model.third_party_app.ThirdParty_Course;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "author")
@Entity
@Builder
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;

    String description;

    String email;

    @OneToMany
    @JoinColumn(name = "author",referencedColumnName = "id")
    @JsonManagedReference("reference1")
    @JsonIgnore
    List<Course> course;



}
