package com.example.api_course_producer.controller;

import com.example.api_course_producer.dto.response.BaseResponse;
import com.example.api_course_producer.dto.response.ResponseFactory;
import com.example.api_course_producer.entity.course.Topic;
import com.example.api_course_producer.service.TopicService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topic")
@CrossOrigin("*")
@RequiredArgsConstructor
public class TopicController {

    private final TopicService topicService;

    @GetMapping()
    public ResponseEntity<BaseResponse<List<Topic>>> getAllTopics() {
        return ResponseFactory.success(topicService.getAllTopics());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<Topic>> getTopicById(@PathVariable("id") int id) {
        return ResponseFactory.success(topicService.getTopicById(id));
    }

    @PostMapping()
    public ResponseEntity<BaseResponse<Topic>> createTopic(@RequestBody Topic topic) {
      return ResponseFactory.success(topicService.createTopic(topic));
    }

    @PutMapping()
    public ResponseEntity<BaseResponse<Topic>> updateTopic(@RequestBody Topic topic) {
        return ResponseFactory.success(topicService.updateTopic(topic));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Void>> deleteTopic(@PathVariable("id") int id) {
        topicService.deleteTopic(id);
        return ResponseFactory.success();
    }
}