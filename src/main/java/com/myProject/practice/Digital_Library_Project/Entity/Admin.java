package com.myProject.practice.Digital_Library_Project.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    @Column(unique = true)
    private  String username;
    private  String password;

    @OneToOne
    @JoinColumn
    @JsonIgnoreProperties("admin")
    private  User user;
}
