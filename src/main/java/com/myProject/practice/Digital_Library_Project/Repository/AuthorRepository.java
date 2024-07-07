package com.myProject.practice.Digital_Library_Project.Repository;

import com.myProject.practice.Digital_Library_Project.Entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    public Author findByEmail(String email);

    public Author findByName(String name);
}
