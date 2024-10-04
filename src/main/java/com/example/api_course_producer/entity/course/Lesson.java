package com.example.api_course_producer.entity.course;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "lessons")
@Entity
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String title;

    String description;

    String content;

    String videoUrl;

    String textUrl;

    @ManyToOne @JoinColumn(name = "chapter_id")
    Chapter chapter;

}
