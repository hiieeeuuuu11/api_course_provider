package com.example.api_course_producer.entity.course;

import com.example.api_course_producer.entity.user.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "learner")
@Entity
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Learner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
}
