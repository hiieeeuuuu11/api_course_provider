package com.example.api_course_producer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LessonRequest {
    int id;
    String title;

    String description;
    String videoUrl;
    String textUrl;
    String content;
    int chapter_id;

}
