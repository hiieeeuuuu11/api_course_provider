package com.example.api_course_producer.controller.third_party_app;

import com.example.api_course_producer.model.third_party_app.ThirdPartyApplication;
import com.example.api_course_producer.service.third_party_service.ThirdPartyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tpa")
@CrossOrigin("*")
public class ThirdPartyAppController {

    final ThirdPartyService thirdPartyService;

    public ThirdPartyAppController(ThirdPartyService thirdPartyService) {
        this.thirdPartyService = thirdPartyService;
    }

    @GetMapping("/getall")
    public ResponseEntity<List<ThirdPartyApplication>> getall(){
        return new ResponseEntity<>(thirdPartyService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/getbyid")
    public ResponseEntity<ThirdPartyApplication> getbyid(@RequestParam("id") int id){
        return new ResponseEntity<>(thirdPartyService.getById(id), HttpStatus.CREATED);
    }

    @PostMapping("/add")
    public ResponseEntity<ThirdPartyApplication> add(@RequestBody ThirdPartyApplication thirdPartyApplication){
        return new ResponseEntity<>(thirdPartyService.add(thirdPartyApplication), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<ThirdPartyApplication> update(@RequestBody ThirdPartyApplication thirdPartyApplication){
        ThirdPartyApplication thirdPartyApplication1 = thirdPartyService.update(thirdPartyApplication);
        if(thirdPartyApplication1!=null)
            return new ResponseEntity<>(thirdPartyService.update(thirdPartyApplication), HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/del")
    public ResponseEntity<Void> deleteTopic(@RequestParam("id") int id) {
        thirdPartyService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
