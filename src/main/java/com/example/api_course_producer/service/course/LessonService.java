package com.example.api_course_producer.service.course;

import com.example.api_course_producer.dto.LessonRequest;
import com.example.api_course_producer.entity.course.Lesson;

import java.util.List;
import java.util.Map;

public interface LessonService {

  Lesson addLesson(Lesson lesson);

  Lesson addLessonToChapter(LessonRequest lessonRequest);

  Lesson updateLesson(Lesson lesson);

  void deleteLesson(int id);

  Map<String, String> getPresignUrlFromS3(String type);

  List<Lesson> getallLesson();

  List<Lesson> getLessonbyChapter(int chapter_id);

  Lesson getLessonbyId(int lesson_id);

  Lesson getLessonbyCourseChapter(int lesson_id);
}
