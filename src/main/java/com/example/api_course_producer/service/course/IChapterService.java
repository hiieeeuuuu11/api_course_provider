package com.example.api_course_producer.service.course;

import com.example.api_course_producer.dto.ChapterRequest;
import com.example.api_course_producer.entity.course.Chapter;
import com.example.api_course_producer.entity.course.Course;
import com.example.api_course_producer.repository.ChapterRepository;
import com.example.api_course_producer.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

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
        Course course = courseRepository.findById(chapterDetailInfomation.getCourse_id()).orElseThrow(NullPointerException::new);
        List<Chapter> chapters = chapterRepository.findChaptersByCourse_Id(course.getId());
        int numberOfChapter = chapters.size();
        Chapter chapter = Chapter.builder()
                .title(chapterDetailInfomation.getTitle())
                .description(chapterDetailInfomation.getDescription())
                .position(numberOfChapter+1)
                .course(course)
                .build();
        return chapterRepository.save(chapter);
    }

    @Override
    public Chapter updateChapter(Chapter chapter)                                                                              {
        Chapter chapter1 = chapterRepository.findById(chapter.getId()).orElse(null);
        if (chapter1 != null) {
            chapter1.setTitle(chapter.getTitle());
            chapter1.setDescription(chapter.getDescription());
            return chapterRepository.save(chapter1);
        }
        return null;
    }

    @Override
    public void deleteChapter(int id) {
        chapterRepository.deleteById(id);
    }


    @Override
    public List<Chapter> getallChapter () {
        return  chapterRepository.findAll();
    }
    @Override
    public List<Chapter> getChapterbyCourse(int course_id){
        return chapterRepository.findChaptersByCourse_Id(course_id);
    }

    @Override
    public int getNumberOfChapter(int course_id) {
        return chapterRepository.countChapterByCourse_Id(course_id);
    }

    @Override
    public Chapter getChapterbyId(int id){
        return chapterRepository.findById(id).orElse(null);
    }

}
