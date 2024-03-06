package com.example.api_course_producer.model.course_model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "lesson")
@Entity
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    String title;

    String description;

    String videoUrl;
    String textUrl;

    @ManyToOne @JoinColumn(name = "chapter_id")
    Chapter chapter_id;


}
