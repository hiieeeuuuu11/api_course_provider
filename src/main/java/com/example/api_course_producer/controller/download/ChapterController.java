package com.example.api_course_producer.controller.download;

import com.example.api_course_producer.model.course.Chapter;
import com.example.api_course_producer.service.download.IChapterDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/download/chapter")
public class ChapterController {
    @Autowired
    IChapterDownloadService iChapterDownloadService;



    @GetMapping("/getallchapter")
    public ResponseEntity<List<Chapter>> getallChapter(){
        List<Chapter> chapterList = iChapterDownloadService.getallChapter();
        return ResponseEntity.ok(chapterList);
    }
    @GetMapping("/getchapterbycourse")
    public ResponseEntity<List<Chapter>> getChapterbyCourse(@RequestParam("course-id") int course_id){
        List<Chapter> chapterList=iChapterDownloadService.getChapterbyCourse(course_id);
        return  ResponseEntity.ok(chapterList);
    }

}

