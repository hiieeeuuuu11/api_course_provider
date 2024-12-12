package com.example.api_course_producer.controller.course;

import com.example.api_course_producer.dto.LearnerResponse;
import com.example.api_course_producer.dto.ProviderResponse;
import com.example.api_course_producer.dto.response.BaseResponse;
import com.example.api_course_producer.dto.response.ResponseFactory;
import com.example.api_course_producer.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AdminController {
  private final AdminService adminService;
  @GetMapping("getNumberCourse")
  public ResponseEntity<BaseResponse<Long>> getChapterById() {

    return ResponseFactory.success(adminService.getNumberCourse());
  }

  @GetMapping("getNumberProvider")
  public ResponseEntity<BaseResponse<Long>> getNumberProvider() {

    return ResponseFactory.success(adminService.getNumberProvider());
  }


  @GetMapping("getNumberEnroll")
  public ResponseEntity<BaseResponse<Long>> getNumberEnroll() {

    return ResponseFactory.success(adminService.getNumberEnroll());
  }

  @GetMapping("getTopLearners")
  public ResponseEntity<BaseResponse<List<LearnerResponse>>> getTopLearners() {

    return ResponseFactory.success(adminService.getTopLearners());
  }

  @GetMapping("getTopProviders")
  public ResponseEntity<BaseResponse<List<ProviderResponse>>> getTopProviders() {

    return ResponseFactory.success(adminService.getTopProviders());
  }

  @GetMapping("getNumberLearner")
  public ResponseEntity<BaseResponse<Long>> getNumberLearner() {

    return ResponseFactory.success(adminService.getNumberLearner());
  }

}
