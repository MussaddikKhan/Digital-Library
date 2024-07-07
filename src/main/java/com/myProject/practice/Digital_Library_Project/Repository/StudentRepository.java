package com.myProject.practice.Digital_Library_Project.Repository;

import com.myProject.practice.Digital_Library_Project.Dto.UpdateStudentRequest;
import com.myProject.practice.Digital_Library_Project.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {
     public  Student findByRollNo(String rollNo);
    @Transactional
    @Modifying
    @Query("UPDATE Student s SET s.name = :#{#updateStudentRequest.name}, s.phone_Number = :#{#updateStudentRequest.mobile }, s.age = :#{#updateStudentRequest.age}, s.email = :#{#updateStudentRequest.email} WHERE s.rollNo = :rollNo")
    public void updateStudent(@Param("rollNo") String rollNo,@Param("updateStudentRequest") UpdateStudentRequest updateStudentRequest);
}
