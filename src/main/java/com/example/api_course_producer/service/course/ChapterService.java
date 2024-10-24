package com.example.api_course_producer.service.course;

import com.example.api_course_producer.dto.ChapterRequest;
import com.example.api_course_producer.entity.course.Chapter;

import java.util.List;

public interface ChapterService {

    Chapter addChapter(Chapter chapter);

    Chapter addChapterToCourse(ChapterRequest chapterDetailInfomation);

    Chapter updateChapter(Chapter chapter);

    void deleteChapter(int id);

    List<Chapter> getallChapter();

    public List<Chapter> getChapterbyCourse(int course_id);

    public Chapter getChapterbyId(int id);

    int getNumberOfChapter(int course_id);

}
