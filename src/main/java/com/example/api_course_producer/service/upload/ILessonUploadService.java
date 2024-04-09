package com.example.api_course_producer.service.upload;

import com.amazonaws.HttpMethod;
import com.example.api_course_producer.dto.LessonRequest;
import com.example.api_course_producer.model.course.Chapter;
import com.example.api_course_producer.model.course.Lesson;
import com.example.api_course_producer.repository.ChapterRepository;
import com.example.api_course_producer.repository.LessonRepository;
import com.example.api_course_producer.service.cloud.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ILessonUploadService implements LessonUploadService{

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
}
