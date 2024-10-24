package com.example.api_course_producer.service;

import com.example.api_course_producer.entity.course.Topic;
import com.example.api_course_producer.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {

  private final TopicRepository topicRepository;

  @Autowired
  public TopicService(TopicRepository topicRepository) {
    this.topicRepository = topicRepository;
  }

  public List<Topic> getAllTopics() {
    return topicRepository.findAll();
  }

  public Topic getTopicById(int id) {
    return topicRepository.findById(id).orElseThrow(() -> new RuntimeException("Topic not found"));
  }

  public Topic createTopic(Topic topic) {
      if (topicRepository.existsById(topic.getId())) {
        throw new RuntimeException("Topic already exists");
      }
    return topicRepository.save(topic);
  }

  public Topic updateTopic(Topic updatedTopic) {
    if (!topicRepository.existsById(updatedTopic.getId())) {
      throw new RuntimeException("Topic not found");
    }
    return topicRepository.save(updatedTopic);
  }

  public void deleteTopic(int id) {
    if (!topicRepository.existsById(id)) {
      throw new RuntimeException("Topic not found");
    }
    topicRepository.deleteById(id);
  }
}
