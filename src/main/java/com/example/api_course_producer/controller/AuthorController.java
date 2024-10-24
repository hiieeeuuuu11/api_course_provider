package com.example.api_course_producer.controller;

import com.example.api_course_producer.entity.course.Provider;
import com.example.api_course_producer.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/provider")
@CrossOrigin("*")
public class AuthorController {

    private final ProviderService providerService;

    @Autowired
    public AuthorController(ProviderService providerService) {
        this.providerService = providerService;
    }

    @GetMapping()
    public ResponseEntity<List<Provider>> getAllAuthors() {
        List<Provider> providers = providerService.getAllAuthors();
        return new ResponseEntity<>(providers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Provider> getAuthorById(@PathVariable("id") int id) {
        Provider provider = providerService.getAuthorById(id);
        if (provider != null) {
            return new ResponseEntity<>(provider, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    public ResponseEntity<Provider> createAuthor(@RequestBody Provider provider) {
        Provider createdProvider = providerService.createAuthor(provider);
        return new ResponseEntity<>(createdProvider, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Provider> updateAuthor(@RequestBody Provider provider) {
        Provider updatedProvider = providerService.updateAuthor(provider);
        if (updatedProvider != null) {
            return new ResponseEntity<>(updatedProvider, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable("id") int id) {
        providerService.deleteAuthor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}