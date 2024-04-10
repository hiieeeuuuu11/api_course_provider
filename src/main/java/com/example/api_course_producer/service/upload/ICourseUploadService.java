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
                .description(courseDetailInformation.getDescription())
                .imageUrl(imgUrl)
                .author(authorService.getAuthorById(courseDetailInformation.getAuthor_id()))
                .isPublished(courseDetailInformation.getIsPublished())
                .build();
        return courseRepository.save(course);
    }

    @Override
    public Course updateCourseDetailInformation(CourseRequest courseDetailInformation) {
        Course course = courseRepository.findById(courseDetailInformation.getId()).orElse(null);
        if(course!=null){
            String imgUrl = null;
            if(courseDetailInformation.getMultipartFile()!= null) {
                imgUrl = s3service.uploadFile(courseDetailInformation.getMultipartFile(), "logo");
            }
            else {
                imgUrl = course.getImageUrl();
            }
            course.setTitle(courseDetailInformation.getTitle());
            course.setDescription(courseDetailInformation.getDescription());
            course.setImageUrl(imgUrl);
            course.setAuthor(authorService.getAuthorById(courseDetailInformation.getAuthor_id()));
            course.setIsPublished(courseDetailInformation.getIsPublished());
            return courseRepository.save(course);
        }
        return null;
    }

    @Override
    public void delete(int id) {
        courseRepository.deleteById(id);
    }
}
