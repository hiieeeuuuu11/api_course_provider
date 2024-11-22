package com.example.api_course_producer.controller.course;

import com.example.api_course_producer.dto.ChapterRequest;
import com.example.api_course_producer.dto.ChapterResponse;
import com.example.api_course_producer.dto.CourseRequest;
import com.example.api_course_producer.dto.response.BaseResponse;
import com.example.api_course_producer.dto.response.ResponseFactory;
import com.example.api_course_producer.entity.course.Chapter;
import com.example.api_course_producer.entity.course.Course;
import com.example.api_course_producer.service.course.ChapterService;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chapter")
@CrossOrigin("*")
public class ChapterController {

  @Autowired ChapterService chapterService;

  @PostMapping("/addtocourse")
  public ResponseEntity<BaseResponse<Chapter>> add(@RequestBody ChapterRequest chapterDetailInfomation) {
    return ResponseFactory.success(chapterService.addChapterToCourse(chapterDetailInfomation));
  }

  @PutMapping("/update")
  public ResponseEntity<BaseResponse<ChapterResponse>> updateChapter(@ModelAttribute Chapter chapter) {
    Chapter chapter1 = chapterService.updateChapter(chapter);
    ChapterResponse chapterResponse = new ChapterResponse(chapter1, chapter1.getCourse().getId());
    return ResponseFactory.success(chapterResponse);
  }

  @DeleteMapping("/delete")
  public ResponseEntity<BaseResponse<Void>> deleteAuthor(@RequestParam("id") int id) {
    chapterService.deleteChapter(id);
    return ResponseFactory.success();
  }

  @GetMapping()
  public ResponseEntity<BaseResponse<List<Chapter>>> getAllChapter() {
    return ResponseFactory.success(chapterService.getallChapter());
  }

  @GetMapping("/getchapterbycourse/{course_id}")
  public ResponseEntity<BaseResponse<List<Chapter>>> getChapterByCourse(
      @PathVariable("course_id") int course_id) {
    return ResponseFactory.success(chapterService.getChapterbyCourse(course_id));
  }

  @GetMapping("/{id}")
  public ResponseEntity<BaseResponse<Chapter>> getChapterById(@PathVariable("id") int id) {

    return ResponseFactory.success(chapterService.getChapterbyId(id));
  }

}
