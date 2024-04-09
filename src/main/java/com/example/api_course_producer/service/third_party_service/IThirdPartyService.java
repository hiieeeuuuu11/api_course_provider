package com.example.api_course_producer.service.third_party_service;

import com.example.api_course_producer.model.third_party_app.ThirdPartyApplication;
import com.example.api_course_producer.repository.ThirdPartyAppRepository;
import com.example.api_course_producer.service.download.CourseDownloadService;
import com.example.api_course_producer.service.download.LessonDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IThirdPartyService implements ThirdPartyService{

    @Autowired
    LessonDownloadService lessonDownloadService;

    @Autowired
    CourseDownloadService courseDownloadService;

    @Autowired
    ThirdPartyAppRepository thirdPartyAppRepository;


    @Override
    public ThirdPartyApplication getById(int id) {
        return thirdPartyAppRepository.findById(id).orElse(null);
    }

    @Override
    public ThirdPartyApplication update(ThirdPartyApplication thirdPartyApplication) {
        if(getById(thirdPartyApplication.getId())!=null)
        return thirdPartyAppRepository.save(thirdPartyApplication);
        else return null;
    }

    @Override
    public ThirdPartyApplication add(ThirdPartyApplication thirdPartyApplication) {
        return thirdPartyAppRepository.save(thirdPartyApplication);
    }



    @Override
    public ThirdPartyApplication findByAddress(String address) {
        return thirdPartyAppRepository.findByAppAddress(address);
    }

    @Override
    public void delete(int id) {
        thirdPartyAppRepository.deleteById(id);
    }

    @Override
    public List<ThirdPartyApplication> getAll() {
        return thirdPartyAppRepository.findAll();
    }
}
