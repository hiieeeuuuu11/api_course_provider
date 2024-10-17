package com.example.api_course_producer.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ResponseStatusCodeConst {
  SUCCESS(HttpStatus.OK.value(), "Thành công"),;
  private final int httpCode;
  private final String message;
}
