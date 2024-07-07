package com.myProject.practice.Digital_Library_Project.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStudentRequest {
    private  String name;
    private  String mobile;
    private  int age;
    private  String email; 

}
