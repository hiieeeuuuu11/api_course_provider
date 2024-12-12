package com.example.api_course_producer.repository;

import com.example.api_course_producer.entity.course.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Integer> {
  @Query(value = "SELECT COUNT( e.student_id) " +
      "FROM enrollments e " +
      "JOIN courses c ON e.course_id = c.id " +
      "WHERE c.provider_id = :providerId AND e.course_id IN :courseIds",
      nativeQuery = true)
  int countStudentsByCourseIdsAndProvider(@Param("courseIds") List<Integer> courseIds,
                                          @Param("providerId") int providerId);

  @Query(value = "SELECT SUM(c.price * enrollment_count) AS total_revenue " +
      "FROM ( " +
      "    SELECT COUNT(DISTINCT e.id) AS enrollment_count, e.course_id " +
      "    FROM enrollments e " +
      "    JOIN courses c ON e.course_id = c.id " +
      "    WHERE c.provider_id = :providerId " +
      "    AND e.course_id IN :courseIds " +
      "    GROUP BY e.course_id " +
      ") AS enrollment_summary " +
      "JOIN courses c ON enrollment_summary.course_id = c.id",
      nativeQuery = true)
  int calculateTotalRevenue(@Param("providerId") int providerId,
                            @Param("courseIds") List<Integer> courseIds);



    @Query("SELECT p.name, COUNT(c) AS course_count " +
        "FROM Provider p LEFT JOIN Course c ON c.provider.id = p.id " +
        "GROUP BY p.id " +
        "ORDER BY course_count DESC LIMIT 5")
    List<Object[]> findTopProvidersByCourseCount();
  }
