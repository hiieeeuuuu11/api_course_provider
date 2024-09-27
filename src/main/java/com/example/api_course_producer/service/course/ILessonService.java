package com.example.api_course_producer.service.course;

import com.amazonaws.HttpMethod;
import com.example.api_course_producer.dto.LessonRequest;
import com.example.api_course_producer.entity.course.Chapter;
import com.example.api_course_producer.entity.course.Lesson;
import com.example.api_course_producer.repository.ChapterRepository;
import com.example.api_course_producer.repository.LessonRepository;
import com.example.api_course_producer.service.cloud.S3Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ILessonService implements LessonService {

    @Autowired
    ChapterRepository chapterRepository;

    @Autowired
    LessonRepository lessonRepository;

    @Autowired
    S3Service s3service;

    @Override
    public Lesson addLesson(Lesson lesson) {
        return null;
    }

    @Override
    public Map<String, String> getPresignUrlFromS3(String type){
        Map<String,String> response = new HashMap<>();
        String fileName = type + "/" + System.currentTimeMillis();
        String url = s3service.generatePreSignedUrl(fileName, HttpMethod.PUT);
        response.put("url",url);
        response.put("fileName",fileName);
        return response;
    }

    @Override
    public Lesson addLessonToChapter(LessonRequest lessonRequest) {
        Chapter chapter = chapterRepository.findById(lessonRequest.getChapter_id()).orElse(null);
        Lesson lesson = Lesson.builder()
                .title(lessonRequest.getTitle())
                .description(lessonRequest.getDescription())
                .videoUrl(lessonRequest.getVideoUrl())
                .textUrl(lessonRequest.getTextUrl())
                .content(lessonRequest.getContent())
                .build();
        chapter.getLessons().add(lesson);
        chapterRepository.save(chapter);
        return lesson;
    }

    @Override
    public Lesson updateLesson(Lesson lesson) {
        Lesson lesson1 = lessonRepository.findById(lesson.getId()).orElse(null);
        if(lesson1!=null){
            if(lesson1.getVideoUrl() != null) {
                s3service.deleteFileFromS3Bucket(lesson1.getVideoUrl());
            }
            if(lesson1.getTextUrl() != null){
                s3service.deleteFileFromS3Bucket(lesson1.getTextUrl());
            }
            return lessonRepository.save(lesson);
        }
        return null;
    }

    @Override
    public void deleteLesson(int id) {
        Lesson lesson1 = lessonRepository.findById(id).orElse(null);
        if(lesson1!=null){
            if(lesson1.getVideoUrl() != null) {
                s3service.deleteFileFromS3Bucket(lesson1.getVideoUrl());
            }
            if(lesson1.getTextUrl() != null){
                s3service.deleteFileFromS3Bucket(lesson1.getTextUrl());
            }
        }
        lessonRepository.deleteById(id);
    }


    @Override
    public Lesson getLessonbyCourseChapter(int lesson_id) {
        return null;
    }

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
