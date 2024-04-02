package com.example.api_course_producer.service.upload;

import com.example.api_course_producer.dto.ChapterRequest;
import com.example.api_course_producer.model.course.Chapter;

public interface ChapterUploadService {

    Chapter addChapter(Chapter chapter);

    Chapter addChapterToCourse(ChapterRequest chapterDetailInfomation);

    Chapter updateChapter(Chapter chapter);

}
