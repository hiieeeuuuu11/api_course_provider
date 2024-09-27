package com.example.api_course_producer.entity.third_party_app;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "thirdpartyapp")
@Entity
@Builder
public class ThirdPartyApplication {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String appName;

    String appAddress;

    String email;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "thirdPartyApplication")
    @JsonIgnore
    List<ThirdParty_Course> thirdParty_course;

}
