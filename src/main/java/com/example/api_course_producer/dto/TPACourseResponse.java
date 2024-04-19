package com.example.api_course_producer.dto;

import com.example.api_course_producer.model.course.Author;
import com.example.api_course_producer.model.course.Chapter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TPACourseResponse {

    int course_id;

    String title;

    String description;

    String imageUrl;

    String topic;

    Author author;

    List<TPAChapterResponse> chapters;
}
