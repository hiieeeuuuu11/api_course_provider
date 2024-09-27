package com.example.api_course_producer.controller.course;

import com.example.api_course_producer.dto.ChapterRequest;
import com.example.api_course_producer.dto.ChapterResponse;
import com.example.api_course_producer.entity.course.Chapter;
import com.example.api_course_producer.service.course.ChapterService;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chapter")
@CrossOrigin("*")
public class ChapterController {

    @Autowired
    ChapterService chapterService;

    @PostMapping("/addtocourse")
    public ResponseEntity<Chapter> add(@ModelAttribute ChapterRequest chapterDetailInfomation) {
        Chapter chapter = chapterService.addChapterToCourse(chapterDetailInfomation);
        return ResponseEntity.ok(chapter);
    }

    @PostMapping("/update")
    public ResponseEntity<ChapterResponse> updateChapter(@RequestBody Chapter chapter) {
        Chapter chapter1 = chapterService.updateChapter(chapter);
        ChapterResponse chapterResponse = new ChapterResponse(chapter1,chapter1.getCourse_id().getId());
        if (Objects.isNull(chapter1)) {
            return new ResponseEntity<>(chapterResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteAuthor(@RequestParam("id") int id) {
        chapterService.deleteChapter(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping()
    public ResponseEntity<List<Chapter>> getAllChapter(){
        List<Chapter> chapterList = chapterService.getallChapter();
        return ResponseEntity.ok(chapterList);
    }

    @GetMapping("/getchapterbycourse")
    public ResponseEntity<List<Chapter>> getChapterByCourse(@RequestParam("course-id") int course_id){
        List<Chapter> chapterList= chapterService.getChapterbyCourse(course_id);
        return  ResponseEntity.ok(chapterList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Chapter> getChapterById(@PathVariable("id") int id){
        Chapter chapter= chapterService.getChapterbyId(id);
        return  ResponseEntity.ok(chapter);
    }


}
