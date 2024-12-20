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
    if (!chapterRepository.existsById(lessonRequest.getChapterId())) {
      throw new RuntimeException("Chapter not found");
    }
    return chapterRepository
        .findById(lessonRequest.getChapterId())
        .map(
            chapter1 -> {
              Lesson lesson =
                  Lesson.builder()
                      .title(lessonRequest.getTitle())
                      .videoUrl(lessonRequest.getVideoUrl())
                      .textUrl(lessonRequest.getTextUrl())
                      .chapter(chapter1)
                      .build();
              return lessonRepository.save(lesson);
            })
        .orElse(null);
  }

  @Override
  public Lesson updateLesson(Lesson lesson) {
    if (!lessonRepository.existsById(lesson.getId())) {
      throw new RuntimeException("Lesson not found");
    }
    return lessonRepository
        .findById(lesson.getId())
        .map(
            lesson1 -> {
              lesson1.setTitle(lesson.getTitle());
              lesson1.setVideoUrl(lesson.getVideoUrl());
              lesson1.setTextUrl(lesson.getTextUrl());
              return lessonRepository.save(lesson1);
            })
        .orElse(null);
  }

  @Override
  public void deleteLesson(int id) {
    if (!lessonRepository.existsById(id)) {
      throw new RuntimeException("Lesson not found");
    }
    lessonRepository
        .findById(id)
        .ifPresent(
            lesson -> {
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
    if (!chapterRepository.existsById(chapter_id)) {
      throw new RuntimeException("Chapter not found");
    }
    return lessonRepository.findLessonsByChapter_Id(chapter_id);
  }

  @Override
  public Lesson getLessonbyId(int lesson_id) {
    if (!lessonRepository.existsById(lesson_id)) {
      throw new RuntimeException("Lesson not found");
    }
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
