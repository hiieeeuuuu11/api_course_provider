package com.example.api_course_producer.model.course_model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "chapter")
@Entity
public class Chapter {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    String title;

    int position;

    String description;

    @ManyToOne @JoinColumn(name = "course_id") @JsonBackReference
    Course course_id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "chapter_id",referencedColumnName = "id")
    @JsonBackReference
    private List<Lesson> lessons;

}
