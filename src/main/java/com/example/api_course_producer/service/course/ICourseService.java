package com.example.api_course_producer.service.course;

import com.example.api_course_producer.dto.CourseRequest;
import com.example.api_course_producer.entity.course.Course;
import com.example.api_course_producer.repository.CourseRepository;
import com.example.api_course_producer.service.ProviderService;
import com.example.api_course_producer.service.cloud.S3Service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ICourseService implements CourseService {

  @Autowired S3Service s3service;

  @Autowired CourseRepository courseRepository;

  @Autowired ProviderService providerService;

  public Course addCourse(Course course) {
    if (courseRepository.existsById(course.getId())) {
      throw new RuntimeException("Course already exists");
    }
    return courseRepository.save(course);
  }

  @Override
  public Course updateCourse(Course course) {
    if (!courseRepository.existsById(course.getId())) {
      throw new RuntimeException("Course not found");
    }
    return courseRepository.save(course);
  }

  @Override
  public Course addCourseDetailInformation(CourseRequest courseDetailInformation) {
    String imgUrl =
        Optional.ofNullable(courseDetailInformation.getMultipartFile())
            .map(file -> s3service.uploadFile(file, "logo"))
            .orElse(null);

    return courseRepository.save(
        Course.builder()
            .title(courseDetailInformation.getTitle())
            .description(courseDetailInformation.getDescription())
            .imageUrl(imgUrl)
            .provider(providerService.getProviderById(1))
            .isPublished(courseDetailInformation.getIsPublished())
            .price(courseDetailInformation.getPrice())
            .build());
  }

  public Course updateCourseDetailInformation(CourseRequest courseDetailInformation) {
    return courseRepository
        .findById(courseDetailInformation.getId())
        .map(
            course -> {
              String imgUrl =
                  Optional.ofNullable(courseDetailInformation.getMultipartFile())
                      .map(file -> s3service.uploadFile(file, "logo"))
                      .orElse(course.getImageUrl());

              course.setTitle(courseDetailInformation.getTitle());
              course.setDescription(courseDetailInformation.getDescription());
              course.setImageUrl(imgUrl);
              course.setProvider(
                  providerService.getProviderById(courseDetailInformation.getProviderId()));
              course.setIsPublished(courseDetailInformation.getIsPublished());
              return courseRepository.save(course);
            })
        .orElse(null);
  }

  @Override
  public void delete(int id) {
    if (!courseRepository.existsById(id)) {
      throw new RuntimeException("Course not found");
    }
    courseRepository.deleteById(id);
  }

  @Override
  public List<Course> getAllCourse() {
    return courseRepository.findAll();
  }

  @Override
  public List<Course> getCourseByProvider(int provider_id) {
    return courseRepository.findAllByProviderId(provider_id);
  }

  @Override
  public Optional<Course> getCourseById(int id) {
    return courseRepository.findById(id);
}

}
