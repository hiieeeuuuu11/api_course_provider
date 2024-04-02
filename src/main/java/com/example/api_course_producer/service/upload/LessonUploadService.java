package com.example.api_course_producer.service.upload;

import com.example.api_course_producer.dto.LessonRequest;
import com.example.api_course_producer.model.course.Lesson;

import java.util.Map;

public interface LessonUploadService {

    Lesson addLesson(Lesson lesson);

    Lesson addLessonToChapter(LessonRequest lessonRequest);

    Map<String, String> getPresignUrlFromS3(String type);

}
