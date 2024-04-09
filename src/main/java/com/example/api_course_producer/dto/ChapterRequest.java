package com.example.api_course_producer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChapterRequest {
    int id;

    String title;

    int position;

    String description;

    int course_id;

}
