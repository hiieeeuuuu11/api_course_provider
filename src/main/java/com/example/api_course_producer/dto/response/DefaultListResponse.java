package com.example.api_course_producer.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DefaultListResponse<T> {

  private Boolean success;
  private String message;
  private List<T> data;

  public void success() {
    this.setMessage("Thành công");
    this.setSuccess(Boolean.TRUE);
  }

  public static <T> DefaultListResponse<T> success(List<T> data) {
    DefaultListResponse<T> response = new DefaultListResponse<>();
    response.success();
    response.setData(data);
    return response;
  }

  public static <T> DefaultListResponse<T> error(String message) {
    DefaultListResponse<T> response = new DefaultListResponse<>();
    response.setSuccess(Boolean.FALSE);
    response.setMessage(message);
    return response;
  }
}