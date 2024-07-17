package com.myProject.practice.Digital_Library_Project.Dto;

import com.myProject.practice.Digital_Library_Project.Entity.Student;
import jakarta.validation.constraints.*;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@ToString
public class CreateStudentRequest {
    @NotBlank
    private String name;
    @NotBlank
    private  String username;
    private  String password;
    @Email
    private String email;

    @Min(18)
    private Integer age;

    @NotBlank
    private String rollNo;
    private String mobile;
    public Student to(){
        return Student.builder()
                .username(this.username)
                .password(this.password)
                .name(this.name)
                .email(this.email)
                .age(this.age)
                .rollNo(this.rollNo)
                .phone_Number(this.mobile)
                .build();
    }
}
