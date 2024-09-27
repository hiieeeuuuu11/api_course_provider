package com.example.api_course_producer.entity.course;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "test")
@Entity
@Builder
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String title;

    String description;

    String question;

    String answer;

    @ManyToOne @JoinColumn(name = "chapter_id") @JsonBackReference("reference1")
    Chapter chapter_id;


}
