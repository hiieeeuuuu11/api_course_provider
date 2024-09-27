package com.example.api_course_producer.service.download;

import com.amazonaws.HttpMethod;
import com.example.api_course_producer.entity.course.Chapter;
import com.example.api_course_producer.entity.course.Lesson;
import com.example.api_course_producer.repository.ChapterRepository;
import com.example.api_course_producer.repository.LessonRepository;
import com.example.api_course_producer.service.cloud.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ILessonDownloadService implements LessonDownloadService{
    @Autowired
    LessonRepository lessonRepository;

    @Override
    public Lesson getLessonbyCourseChapter(int lesson_id) {
        return null;
    }

    @Autowired
    ChapterRepository chapterRepository;

    @Autowired
    S3Service s3service;

    @Override
    public List<Lesson> getallLesson(){
        return lessonRepository.findAll();
    }

    @Override
    public List<Lesson> getLessonbyChapter(int chapter_id){
        Chapter chapter  = chapterRepository.findById(chapter_id).orElse(null);
        return chapter.getLessons();
    }

    @Override
    public Lesson getLessonbyId(int lesson_id){
        Lesson lesson  = lessonRepository.findById(lesson_id).orElse(null);
        if(lesson.getVideoUrl() != null) {
            String videoUrl= s3service.generatePreSignedUrl(lesson.getVideoUrl(), HttpMethod.GET);
            lesson.setVideoUrl(videoUrl);
        }
        if(lesson.getTextUrl() != null){
            String textUrl = s3service.generatePreSignedUrl(lesson.getTextUrl(),HttpMethod.GET);
            lesson.setTextUrl(textUrl);
        }
        return lesson;
    }



}
