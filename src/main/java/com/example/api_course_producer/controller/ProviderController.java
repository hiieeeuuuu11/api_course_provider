package com.example.api_course_producer.controller;

import com.example.api_course_producer.dto.Top4Response;
import com.example.api_course_producer.dto.response.BaseResponse;
import com.example.api_course_producer.dto.response.ResponseFactory;
import com.example.api_course_producer.entity.course.Provider;
import com.example.api_course_producer.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/provider")
@CrossOrigin("*")
public class ProviderController {

  private final ProviderService providerService;

  @Autowired
  public ProviderController(ProviderService providerService) {
    this.providerService = providerService;
  }

  @GetMapping()
  public ResponseEntity<BaseResponse<List<Provider>>> getAlls() {
    return ResponseFactory.success(providerService.getAllProviders());
  }

  @GetMapping("/{id}")
  public ResponseEntity<BaseResponse<Provider>> getProviderById(@PathVariable("id") int id) {
    return ResponseFactory.success(providerService.getProviderById(id));
  }

  @PostMapping()
  public ResponseEntity<Provider> createProvider(@RequestBody Provider provider) {
    Provider createdProvider = providerService.createProvider(provider);
    return new ResponseEntity<>(createdProvider, HttpStatus.CREATED);
  }

  @PutMapping()
  public ResponseEntity<BaseResponse<Provider>> updateProvider(@RequestBody Provider provider) {
    return ResponseFactory.success(providerService.updateProvider(provider));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<BaseResponse<Void>> deleteProvider(@PathVariable("id") int id) {
    providerService.deleteProvider(id);
    return ResponseFactory.success();
  }

  @GetMapping("/countCourseByProvider/{provider_id}")
  public ResponseEntity<BaseResponse<Integer>> countCourseByProvider(@PathVariable("provider_id") int provider_id) {
    return ResponseFactory.success(providerService.countCourseByProvider(provider_id));
  }

  @GetMapping("/getStudentRegister/{provider_id}")
  public ResponseEntity<BaseResponse<Integer>> getStudentRegister(@PathVariable("provider_id") int provider_id) {
    return ResponseFactory.success(providerService.getStudentRegister(provider_id));
  }

  @GetMapping("/getTotalRevenue/{provider_id}")
  public ResponseEntity<BaseResponse<Integer>> getTotalRevenue(@PathVariable("provider_id") int provider_id) {
    return ResponseFactory.success(providerService.calculateTotalRevenue(provider_id));
  }

  @GetMapping("/get4course/{provider_id}")
  public ResponseEntity<BaseResponse<List<Top4Response>>> get4Course(@PathVariable("provider_id") int provider_id) {
    return ResponseFactory.success(providerService.get4Course(provider_id));
  }

}
