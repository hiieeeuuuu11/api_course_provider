package com.example.api_course_producer.exceptions;

import com.example.api_course_producer.share.enums.ResponseStatusCodeConst;

public class ObjectNotFoundException extends CourseAppException {

  public ObjectNotFoundException(ResponseStatusCodeConst responseStatusCodeConst) {
    super(responseStatusCodeConst);
  }
}
