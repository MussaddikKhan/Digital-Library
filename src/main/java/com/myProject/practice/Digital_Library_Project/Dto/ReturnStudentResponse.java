package com.myProject.practice.Digital_Library_Project.Dto;

import com.myProject.practice.Digital_Library_Project.Entity.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReturnStudentResponse {
    private String name;
    private  Integer age;
    private  String email;
    private  String rollNo;
    private String phone_Number;
    private Date createdOn;
    private  Date updatedOn;

    public static ReturnStudentResponse From(Student student){
        return  ReturnStudentResponse.builder()
                          .age(student.getAge())
                          .name(student.getName())
                          .email(student.getEmail())
                          .rollNo(student.getRollNo())
                          .phone_Number(student.getPhone_Number())
                          .createdOn(student.getCreatedOn())
                          .updatedOn(student.getUpdatedOn())
                .build();
    }
}
