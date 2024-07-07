package com.myProject.practice.Digital_Library_Project.Controller;

import com.myProject.practice.Digital_Library_Project.Dto.*;
import com.myProject.practice.Digital_Library_Project.Services.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping("/create")
    public ReturnStudentResponse createStudent(@Valid @RequestBody CreateStudentRequest student){
        return studentService.createStudent(student);
    }
    @GetMapping("/find/{rollNo}")
    public  ReturnStudentResponse findStudentByRollNo(@PathVariable String rollNo){
        return studentService.getByRollNumber(rollNo);
    }

    @GetMapping("/books/{rollNo}")
    public List<ReturnBookResponse> findBookByStudent(@PathVariable String rollNo){
        return studentService.findBookByStudent(rollNo);
    }
    @GetMapping("/transactions/{rollNo}")
    public  List<ReturnTransactionResponse> getAllTransaction(@PathVariable String rollNo){
        return studentService.getAllTransaction(rollNo);
    }

    @PatchMapping("/update/{rollNo}")
    public  String updateStudent(@PathVariable String rollNo , @RequestBody UpdateStudentRequest updateStudentRequest){
        return studentService.updateStudent(rollNo , updateStudentRequest);
    }


    
}

