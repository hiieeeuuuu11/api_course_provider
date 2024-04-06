package com.example.api_course_producer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

public record TokenRequest(int course_id, int tpa_id, Date start_date,Date end_date) {
}

