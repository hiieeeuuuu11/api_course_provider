package com.example.api_course_producer.controller.course;

import com.example.api_course_producer.dto.ChapterRequest;
import com.example.api_course_producer.dto.ChapterResponse;
import com.example.api_course_producer.dto.response.BaseResponse;
import com.example.api_course_producer.dto.response.ResponseFactory;
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

  @Autowired ChapterService chapterService;

  @PostMapping("/addtocourse")
  public ResponseEntity<BaseResponse<Chapter>> add(@RequestBody Chapter chapterDetailInfomation) {
    return ResponseFactory.success(chapterService.addChapter(chapterDetailInfomation));
  }

  @PostMapping("/update")
  public ResponseEntity<BaseResponse<ChapterResponse>> updateChapter(@RequestBody Chapter chapter) {
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

  @GetMapping("/getchapterbycourse")
  public ResponseEntity<BaseResponse<List<Chapter>>> getChapterByCourse(
      @RequestParam("course-id") int course_id) {
    return ResponseFactory.success(chapterService.getChapterbyCourse(course_id));
  }

  @GetMapping("/{id}")
  public ResponseEntity<BaseResponse<Chapter>> getChapterById(@PathVariable("id") int id) {

    return ResponseFactory.success(chapterService.getChapterbyId(id));
  }
}
