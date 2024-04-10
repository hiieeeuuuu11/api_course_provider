package com.example.api_course_producer.dto;

import com.example.api_course_producer.model.course.Chapter;

public record ChapterResponse(Chapter chapter,int course_id) {
}
