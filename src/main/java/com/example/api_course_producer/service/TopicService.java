package com.example.api_course_producer.service;

import com.example.api_course_producer.model.course.Topic;
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
        return topicRepository.findById(id).orElse(null);
    }

    public Topic createTopic(Topic topic) {
        return topicRepository.save(topic);
    }

    public Topic updateTopic(Topic updatedTopic) {
        return topicRepository.save(updatedTopic);
    }

    public void deleteTopic(int id) {
        topicRepository.deleteById(id);
    }
}