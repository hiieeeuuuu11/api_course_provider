package com.example.api_course_producer.dto;

import java.util.Date;

public record TPA_CourseRequest(int course_id, int tpa_id, Date start_date, Date end_date) {
}

