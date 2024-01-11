package com.myProject.practice.Digital_Library_Project.Repository;

import com.myProject.practice.Digital_Library_Project.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

}
