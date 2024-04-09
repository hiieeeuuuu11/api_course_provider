package com.example.api_course_producer.model.course;

import com.example.api_course_producer.model.third_party_app.ThirdPartyApplication;
import com.example.api_course_producer.model.third_party_app.ThirdParty_Course;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "course")
@Entity
@Builder
public class Course {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String title;

    String description;

    String imageUrl;

    int isPublished;

    String topic;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id",referencedColumnName = "id")
    @JsonManagedReference
    private List<Chapter> chapters;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "course",referencedColumnName = "id")
    @JsonManagedReference
    @JsonIgnore
    List<ThirdParty_Course> thirdParty_course;

    @ManyToOne @JoinColumn(name = "author")
    @JsonBackReference("reference1")
    Author author;

}