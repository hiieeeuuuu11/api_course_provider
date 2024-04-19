package com.example.api_course_producer;

import com.example.api_course_producer.service.MailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class ApiCourseProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiCourseProducerApplication.class, args);

    }


}