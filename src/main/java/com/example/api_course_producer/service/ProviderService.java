package com.example.api_course_producer.service;

import com.example.api_course_producer.dto.Top4Response;
import com.example.api_course_producer.entity.course.Course;
import com.example.api_course_producer.entity.course.Provider;
import com.example.api_course_producer.repository.CourseRepository;
import com.example.api_course_producer.repository.ProviderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class ProviderService {

  private final ProviderRepository ProviderRepository;
  private final CourseRepository courseRepository;
  private final ProviderRepository providerRepository;

  @Autowired
  public ProviderService(
      ProviderRepository ProviderRepository,
      CourseRepository courseRepository,
      ProviderRepository providerRepository) {
    this.ProviderRepository = ProviderRepository;
    this.courseRepository = courseRepository;
    this.providerRepository = providerRepository;
  }

  public List<Provider> getAllProviders() {
    return ProviderRepository.findAll();
  }

  public Provider getProviderById(int id) {
    return ProviderRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Provider not found"));
  }

  public Provider createProvider(Provider provider) {

    return ProviderRepository.save(provider);
  }

  public Provider updateProvider(Provider updatedProvider) {
    Provider existingProvider =
        ProviderRepository.findById(updatedProvider.getId())
            .orElseThrow(() -> new RuntimeException("Provider not found"));
    if (existingProvider != null) {
      existingProvider.setName(updatedProvider.getName());
      existingProvider.setDescription(updatedProvider.getDescription());
      existingProvider.setWebsite(updatedProvider.getWebsite());
      return ProviderRepository.save(existingProvider);
    }
    return null;
  }

  public void deleteProvider(int id) {
    Provider provider =
        ProviderRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Provider not found"));
    if (provider != null) {
      ProviderRepository.delete(provider);
    }
  }

  public int countCourseByProvider(int provider_id) {
    return courseRepository.countByProviderId(provider_id);
  }

  public int getStudentRegister(int provider_id) {
    List<Integer> courseIds =
        courseRepository.findAllByProviderId(provider_id).stream().map(Course::getId).toList();
    return providerRepository.countStudentsByCourseIdsAndProvider(courseIds, provider_id);
  }

  public int calculateTotalRevenue(int providerId) {
    List<Integer> courseIds =
        courseRepository.findAllByProviderId(providerId).stream().map(Course::getId).toList();
    return providerRepository.calculateTotalRevenue(providerId, courseIds);
  }

  public List<Top4Response> get4Course(int provider_id) {
    List<Object[]> courses = courseRepository.find4ByProvider(provider_id);
    return courses.stream()
        .map(
            course -> {
              return Top4Response.builder()
                  .title((String) course[0])
                  .description((String) course[1])
                  .imageUrl((String) course[2])
                  .price((Integer) course[3])
                  .count((Long) course[4])
                  .build();
            })
        .toList();
  }
}
