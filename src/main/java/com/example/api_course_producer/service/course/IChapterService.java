package com.example.api_course_producer.service.course;

import com.example.api_course_producer.dto.ChapterRequest;
import com.example.api_course_producer.entity.course.Chapter;
import com.example.api_course_producer.entity.course.Course;
import com.example.api_course_producer.exceptions.BadRequestException;
import com.example.api_course_producer.repository.ChapterRepository;
import com.example.api_course_producer.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class IChapterService implements ChapterService {

  final ChapterRepository chapterRepository;

  final CourseRepository courseRepository;

  @Override
  public Chapter addChapter(Chapter chapter) {
    return chapterRepository.save(chapter);
  }

  @Override
  public Chapter addChapterToCourse(ChapterRequest chapterDetailInfomation) {
    Course course =
        courseRepository
            .findById(chapterDetailInfomation.getCourse_id())
            .orElseThrow(() -> new RuntimeException("No course found for this id"));
    List<Chapter> chapters =
        chapterRepository.findChaptersByCourse_Id(chapterDetailInfomation.getCourse_id());
    int numberOfChapter = chapters.size();
    Chapter chapter =
        Chapter.builder()
            .title(chapterDetailInfomation.getTitle())
            .description(chapterDetailInfomation.getDescription())
            .position(numberOfChapter + 1)
            .course(course)
            .build();
    return chapterRepository.save(chapter);
  }

  @Override
  public Chapter updateChapter(Chapter chapter) {
    if (!chapterRepository.existsById(chapter.getId())) {
      throw new RuntimeException("No chapter found for this id");
    }
    chapter.setTitle(chapter.getTitle());
    chapter.setDescription(chapter.getDescription());
    return chapterRepository.save(chapter);
  }

  @Override
  public void deleteChapter(int id) {
    if (!chapterRepository.existsById(id)) {
      throw new BadRequestException("No chapter found for this id");
    }
    chapterRepository.deleteById(id);
  }

  @Override
  public List<Chapter> getallChapter() {
    return chapterRepository.findAll();
  }

  @Override
  public List<Chapter> getChapterbyCourse(int course_id) {
    var result = chapterRepository.findChaptersByCourse_Id(course_id);
    if (result.isEmpty()) {
      throw new RuntimeException("No chapter found for this course");
    } else {
      return result;
    }
  }

  @Override
  public int getNumberOfChapter(int course_id) {
    return chapterRepository.countChapterByCourse_Id(course_id);
  }

  @Override
  public Chapter getChapterbyId(int id) {
    if (!chapterRepository.existsById(id)) {
      throw new BadRequestException("No chapter found for this id");
    }
    return chapterRepository.findById(id).get();
  }
}
