package com.example.api_course_producer.exceptions;

import com.example.api_course_producer.constant.ResponseStatusCodeConst;
import lombok.Getter;

@Getter
public abstract class WarehouseException extends RuntimeException {

  private String errorCode = "";

  private static final long serialVersionUID = 1L;

  public WarehouseException(String message){
    super(message);
  }

  protected WarehouseException(String message, Throwable cause) {
    super(message, cause);
  }

  public WarehouseException(ResponseStatusCodeConst responseStatusCodeConst) {
    super(responseStatusCodeConst.getMessage());
    errorCode = responseStatusCodeConst.name().toLowerCase();
  }

  public WarehouseException(ResponseStatusCodeConst responseStatusCodeConst, Object ...params) {
    super(String.format(responseStatusCodeConst.getMessage(), params));
    errorCode = responseStatusCodeConst.name().toLowerCase();
  }

  public WarehouseException(ResponseStatusCodeConst responseStatusCodeConst, Throwable cause) {
    super(responseStatusCodeConst.getMessage(), cause);
    errorCode = responseStatusCodeConst.name().toLowerCase();
  }
}
