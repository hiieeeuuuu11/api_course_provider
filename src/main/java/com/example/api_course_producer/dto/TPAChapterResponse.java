package com.example.api_course_producer.dto;

import com.example.api_course_producer.model.course.Course;
import com.example.api_course_producer.model.course.Lesson;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TPAChapterResponse {


    String title;

    int position;

    String description;

    private List<TPALessonResponse> lessons;
}
