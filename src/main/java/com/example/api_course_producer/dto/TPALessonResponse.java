package com.example.api_course_producer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TPALessonResponse {

    int lesson_id;
    String title;

    String description;

    String content;

    String videoUrl;
    String textUrl;
}
