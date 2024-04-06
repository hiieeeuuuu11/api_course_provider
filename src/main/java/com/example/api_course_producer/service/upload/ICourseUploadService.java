package com.example.api_course_producer.service.upload;

import com.example.api_course_producer.dto.CourseRequest;
import com.example.api_course_producer.model.course.Course;
import com.example.api_course_producer.repository.CourseRepository;
import com.example.api_course_producer.service.AuthorService;
import com.example.api_course_producer.service.cloud.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ICourseUploadService implements CourseUploadService{

    @Autowired
    S3Service s3service;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    AuthorService authorService;

    public Course addCourse(Course course){
        return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course addCourseDetailInformation(CourseRequest courseDetailInformation) {
        String imgUrl = null;
        if(courseDetailInformation.getMultipartFile()!= null) {
            imgUrl = s3service.uploadFile(courseDetailInformation.getMultipartFile(), "logo");
        }
        Course course = Course.builder()
                .title(courseDetailInformation.getTitle())
                .imageUrl(imgUrl)
                .author(authorService.getAuthorById(courseDetailInformation.getAuthor_id()))
                .isPublished(courseDetailInformation.getIsPublished())
                .build();
        return courseRepository.save(course);
    }

    @Override
    public Course updateCourseDetailInformation(CourseRequest courseDetailInformation) {
        String imgUrl = null;
        if(courseDetailInformation.getMultipartFile()!= null) {
            imgUrl = s3service.uploadFile(courseDetailInformation.getMultipartFile(), "logo");
        }
        Course course = Course.builder()
                .id(courseDetailInformation.getId())
                .title(courseDetailInformation.getTitle())
                .imageUrl(imgUrl)
                .isPublished(0)
                .build();
        return courseRepository.save(course);
    }


}
