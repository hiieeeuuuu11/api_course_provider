package com.example.api_course_producer.controller.third_party_app;

import com.example.api_course_producer.model.third_party_app.ThirdPartyApplication;
import com.example.api_course_producer.service.third_party_service.ThirdPartyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tpa")
@CrossOrigin("*")
public class ThirdPartyAppController {

    final ThirdPartyService thirdPartyService;

    public ThirdPartyAppController(ThirdPartyService thirdPartyService) {
        this.thirdPartyService = thirdPartyService;
    }

    @PostMapping("/add")
    public ResponseEntity<ThirdPartyApplication> add(@RequestBody ThirdPartyApplication thirdPartyApplication){
        return new ResponseEntity<>(thirdPartyService.add(thirdPartyApplication), HttpStatus.CREATED);
    }



}
