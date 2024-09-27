package com.example.api_course_producer.service.download;

import com.example.api_course_producer.entity.course.Lesson;

import java.util.List;


public interface LessonDownloadService {
    List<Lesson> getallLesson();

    List<Lesson> getLessonbyChapter(int chapter_id);

    Lesson getLessonbyId(int lesson_id);

    Lesson getLessonbyCourseChapter(int lesson_id);



}
