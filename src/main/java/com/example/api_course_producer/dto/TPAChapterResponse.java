package com.example.api_course_producer.dto;

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
