package com.example.api_course_producer.entity.course;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "courses")
@Entity
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    Provider provider;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    Topic topic;

    @Column(nullable = false)
    String title;

    @Column(columnDefinition = "TEXT")
    String description;

    String imageUrl;

    Integer isPublished;

    Integer price;

    @Column(nullable = true)
    LocalDateTime createdAt;

    @Column(nullable = true)
    LocalDateTime updatedAt;
}