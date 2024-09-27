package com.example.api_course_producer.repository;

import com.example.api_course_producer.entity.third_party_app.ThirdPartyApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThirdPartyAppRepository extends JpaRepository<ThirdPartyApplication,Integer> {

    ThirdPartyApplication findByAppAddress(String address);

}
