package com.example.api_course_producer.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Top4Response {
  private String title;
  private String description;
  private String imageUrl;
  private Integer price;
  private Long count;

}
