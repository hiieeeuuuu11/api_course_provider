package com.example.api_course_producer.service.download;

import com.example.api_course_producer.model.course.Chapter;
import com.example.api_course_producer.model.course.Course;

import java.util.List;

public interface ChapterDownloadService {

    public List<Chapter> getallChapter ();

    public List<Chapter> getChapterbyCourse(int course_id);

    public Chapter getChapterbyId(int id);

    int getNumberOfChapter(int course_id);
}
