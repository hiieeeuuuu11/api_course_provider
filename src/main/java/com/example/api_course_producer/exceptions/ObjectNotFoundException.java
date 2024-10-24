package com.example.api_course_producer.exceptions;

import com.example.api_course_producer.constant.ResponseStatusCodeConst;

public class ObjectNotFoundException extends WarehouseException {

  public ObjectNotFoundException(ResponseStatusCodeConst responseStatusCodeConst) {
    super(responseStatusCodeConst);
  }
}
