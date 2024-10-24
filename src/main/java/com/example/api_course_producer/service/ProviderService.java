package com.example.api_course_producer.service;

import com.example.api_course_producer.entity.course.Provider;
import com.example.api_course_producer.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderService {

    private final AuthorRepository authorRepository;

    @Autowired
    public ProviderService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Provider> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Provider getAuthorById(int id) {
        return authorRepository.findById(id).orElse(null);
    }

    public Provider createAuthor(Provider provider) {
        return authorRepository.save(provider);
    }

    public Provider updateAuthor(Provider updatedProvider) {
        Provider existingProvider = authorRepository.findById(updatedProvider.getId()).orElse(null);
        if (existingProvider != null) {
            existingProvider.setName(updatedProvider.getName());
            existingProvider.setDescription(updatedProvider.getDescription());
            existingProvider.setWebsite(updatedProvider.getWebsite());
            return authorRepository.save(existingProvider);
        }
        return null;
    }

    public void deleteAuthor(int id) {
        authorRepository.deleteById(id);
    }
}
