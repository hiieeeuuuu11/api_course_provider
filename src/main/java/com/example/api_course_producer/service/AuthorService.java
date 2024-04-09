package com.example.api_course_producer.service;

import com.example.api_course_producer.model.course.Author;
import com.example.api_course_producer.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(int id) {
        return authorRepository.findById(id).orElse(null);
    }

    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Author updateAuthor(Author updatedAuthor) {
        Author existingAuthor = authorRepository.findById(updatedAuthor.getId()).orElse(null);
        if (existingAuthor != null) {
            existingAuthor.setName(updatedAuthor.getName());
            existingAuthor.setDescription(updatedAuthor.getDescription());
            existingAuthor.setEmail(updatedAuthor.getEmail());
            return authorRepository.save(existingAuthor);
        }
        return null;
    }

    public void deleteAuthor(int id) {
        authorRepository.deleteById(id);
    }
}
