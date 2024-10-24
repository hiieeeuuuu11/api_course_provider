package com.example.api_course_producer.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BaseResponse<T> {
  private boolean success;
  private T data;
  private String message;
}
