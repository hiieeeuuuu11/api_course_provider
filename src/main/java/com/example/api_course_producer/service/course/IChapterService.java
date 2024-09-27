package com.example.api_course_producer.service.course;

import com.example.api_course_producer.dto.ChapterRequest;
import com.example.api_course_producer.entity.course.Chapter;
import com.example.api_course_producer.entity.course.Course;
import com.example.api_course_producer.repository.ChapterRepository;
import com.example.api_course_producer.repository.CourseRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IChapterService implements ChapterService {

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
        Course course = courseRepository.findById(chapterDetailInfomation.getCourse_id()).orElse(null);
        int numberOfChapter = course.getChapters().size();
        Chapter chapter = Chapter.builder()
                .title(chapterDetailInfomation.getTitle())
                .description(chapterDetailInfomation.getDescription())
                .position(numberOfChapter+1)
                .build();
        course.getChapters().add(chapter);
        courseRepository.save(course);
        //chapterRepository.save(chapter);
        return chapter;
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
        Course course  = courseRepository.findById(course_id).orElse(null);
        return course.getChapters();
    }

    @Override
    public int getNumberOfChapter(int course_id) {
        return courseRepository.getNumberOfChapter(course_id);
    }

    @Override
    public Chapter getChapterbyId(int id){
        Chapter chapter  = chapterRepository.findById(id).orElse(null);
        return chapter;
    }

}
