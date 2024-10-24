package com.example.api_course_producer.entity.course;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "topic")
@Entity
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;

    String description;

}
