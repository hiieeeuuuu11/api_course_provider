package com.example.api_course_producer.repository;

import com.example.api_course_producer.entity.course.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Provider, Integer> {
    // Các phương thức tùy chỉnh có thể được thêm vào đây (nếu cần)
}