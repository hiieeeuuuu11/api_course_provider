package com.example.api_course_producer.service.upload;

import com.example.api_course_producer.dto.ChapterRequest;
import com.example.api_course_producer.model.course.Chapter;
import com.example.api_course_producer.model.course.Course;
import com.example.api_course_producer.repository.ChapterRepository;
import com.example.api_course_producer.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IChapterUploadService implements ChapterUploadService{

    @Autowired
    ChapterRepository chapterRepository;

    @Autowired
    CourseRepository courseRepository;

    @Override
    public Chapter addChapter(Chapter chapter) {
        return chapterRepository.save(chapter);
    }

    @Override
    public Chapter addChapterToCourse(ChapterRequest chapterDetailInfomation) {
        Chapter chapter = Chapter.builder()
                .title(chapterDetailInfomation.getTitle())
                .description(chapterDetailInfomation.getDescription())
                .build();
        Course course = courseRepository.findById(chapterDetailInfomation.getCourse_id()).orElse(null);
        course.getChapters().add(chapter);
        courseRepository.save(course);
        //chapterRepository.save(chapter);
        return chapter;
    }

    @Override
    public Chapter updateChapter(Chapter chapter)                                                                                    {
        return null;
    }
}
