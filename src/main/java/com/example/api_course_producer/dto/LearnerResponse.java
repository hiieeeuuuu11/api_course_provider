package com.example.api_course_producer.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LearnerResponse {
  private String name;
  private Integer course_count;
}
