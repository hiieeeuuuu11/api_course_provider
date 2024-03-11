package com.example.api_course_producer.model.course_model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "course")
@Entity
public class Course {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    String title;

    String description;

    String imageUrl;

    int isPublished;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id",referencedColumnName = "id")
    @JsonBackReference
    private List<Chapter> chapters;

}
