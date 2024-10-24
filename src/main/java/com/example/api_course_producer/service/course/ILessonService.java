package com.example.api_course_producer.service.course;

import com.amazonaws.HttpMethod;
import com.example.api_course_producer.dto.LessonRequest;
import com.example.api_course_producer.entity.course.Lesson;
import com.example.api_course_producer.repository.ChapterRepository;
import com.example.api_course_producer.repository.LessonRepository;
import com.example.api_course_producer.service.cloud.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class ILessonService implements LessonService {

    final ChapterRepository chapterRepository;


    final LessonRepository lessonRepository;

    final S3Service s3service;

    @Override
    public Lesson addLesson(Lesson lesson) {
        return null;
    }

    @Override
    public Map<String, String> getPresignUrlFromS3(String type) {
        Map<String, String> response = new HashMap<>();
        String fileName = type + "/" + System.currentTimeMillis();
        String url = s3service.generatePreSignedUrl(fileName, HttpMethod.PUT);
        response.put("url", url);
        response.put("fileName", fileName);
        return response;
    }

    @Override
    public Lesson addLessonToChapter(LessonRequest lessonRequest) {
        return chapterRepository.findById(lessonRequest.getChapter_id())
                .map(chapter1 -> {
                    Lesson lesson = Lesson.builder()
                            .title(lessonRequest.getTitle())
                            .description(lessonRequest.getDescription())
                            .videoUrl(lessonRequest.getVideoUrl())
                            .textUrl(lessonRequest.getTextUrl())
                            .content(lessonRequest.getContent())
                            .chapter(chapter1)
                            .build();
                    return lessonRepository.save(lesson);
                }).orElse(null);
    }

    @Override
    public Lesson updateLesson(Lesson lesson) {
        return lessonRepository.findById(lesson.getId())
                .map(lesson1 -> {
                    lesson1.setTitle(lesson.getTitle());
                    lesson1.setDescription(lesson.getDescription());
                    lesson1.setVideoUrl(lesson.getVideoUrl());
                    lesson1.setTextUrl(lesson.getTextUrl());
                    lesson1.setContent(lesson.getContent());
                    return lessonRepository.save(lesson1);
                }).orElse(null);
    }

    @Override
    public void deleteLesson(int id) {
        lessonRepository.findById(id).ifPresent(lesson -> {
            if (lesson.getVideoUrl() != null) {
                s3service.deleteFileFromS3Bucket(lesson.getVideoUrl());
            }
            if (lesson.getTextUrl() != null) {
                s3service.deleteFileFromS3Bucket(lesson.getTextUrl());
            }
        });
        lessonRepository.deleteById(id);
    }


    @Override
    public Lesson getLessonbyCourseChapter(int lesson_id) {
        return null;
    }

    @Override
    public List<Lesson> getallLesson() {
        return lessonRepository.findAll();
    }

    @Override
    public List<Lesson> getLessonbyChapter(int chapter_id) {
        return lessonRepository.findLessonsByChapter_Id(chapter_id);
    }

    @Override
    public Lesson getLessonbyId(int lesson_id) {
        Lesson lesson = lessonRepository.findById(lesson_id).orElse(null);
        if (lesson.getVideoUrl() != null) {
            String videoUrl = s3service.generatePreSignedUrl(lesson.getVideoUrl(), HttpMethod.GET);
            lesson.setVideoUrl(videoUrl);
        }
        if (lesson.getTextUrl() != null) {
            String textUrl = s3service.generatePreSignedUrl(lesson.getTextUrl(), HttpMethod.GET);
            lesson.setTextUrl(textUrl);
        }
        return lesson;
    }

}
